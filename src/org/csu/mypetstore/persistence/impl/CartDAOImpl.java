package org.csu.mypetstore.persistence.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.csu.mypetstore.domain.*;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.service.CatalogService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CartDAOImpl implements CartDAO {
    private static final String GET_CART_ITEMS_BY_USERNAME = "SELECT ITEMID, QUANTITY, VALID FROM CARTITEM WHERE USERNAME = ?";
    private static final String CHECK_IF_DB_CONTAINS_PRODUCT_ID = "SELECT ITEMID, QUANTITY, VALID FROM CARTITEM WHERE USERNAME = ? AND ITEMID = ?";
    private static final String INSERT_CARD_ITEM = "INSERT INTO CARTITEM (USERNAME, ITEMID, QUANTITY) VALUES (?, ?, ?)";
    private static final String UPDATE_CART_ITEM_QUANTITY = "UPDATE CARTITEM SET QUANTITY = ? WHERE USERNAME = ? AND ITEMID = ?";
    private static final String SET_CART_ITEM_ISVALID = "UPDATE CARTITEM SET VALID = ? WHERE USERNAME = ? AND ITEMID = ?";


    CatalogService catalogService;

    @Override
    public Cart getCartByUsername(String username) {
        List<CartItemDB> cartItemDBList = new ArrayList<>();
        catalogService = new CatalogService();

        Cart cart = new Cart();
        boolean isInStock;

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_CART_ITEMS_BY_USERNAME);
            pStatement.setString(1, username);
            ResultSet resultSet = pStatement.executeQuery();


            while( resultSet.next() ){
                CartItemDB cartItemDB = new CartItemDB();
                cartItemDB.setItemId(resultSet.getString(1));
                cartItemDB.setQuantity(resultSet.getInt(2));
                cartItemDB.setValid(resultSet.getBoolean(3));

                if(cartItemDB.isValid()){
                    isInStock = catalogService.isItemInStock(cartItemDB.getItemId());
                    Item item = catalogService.getItem(cartItemDB.getItemId());
                    item.setQuantity( cartItemDB.getQuantity() );
                    cart.addItem(item,isInStock);
                    cartItemDBList.add(cartItemDB);
                }
            }

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }


        return cart;
    }

    @Override
    public void addItemToCart(String itemId, String username) {
        try{
            Connection connection = DBUtil.getConnection();
            Cart cart = getCartByUsername(username);
            if(cart == null){
                cart = new Cart();
            }

            if(cart.containsItemId(itemId)){
                PreparedStatement updateStatement = connection.prepareStatement(UPDATE_CART_ITEM_QUANTITY);
                cart.incrementQuantityByItemId(itemId);
                int quantity = cart.getQuantityByItemId(itemId);
                updateStatement.setInt(1, quantity );
                updateStatement.setString(2, username);
                updateStatement.setString(3, itemId);
                updateStatement.executeUpdate();

                DBUtil.closePreparedStatement(updateStatement);
                DBUtil.closeConnection(connection);
            }else if ( checkIfDbCartContainsItem(itemId, username) ) {
                updateItemValid(username, itemId, true);
                updateItemQuantity(username, itemId, 1);
            } else {
                PreparedStatement insertStatement = connection.prepareStatement(INSERT_CARD_ITEM);
                insertStatement.setString(1, username);
                insertStatement.setString(2, itemId);
                insertStatement.setInt(3, 1);
                insertStatement.executeUpdate();

                DBUtil.closePreparedStatement(insertStatement);
                DBUtil.closeConnection(connection);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateItemValid(String username, String itemId, boolean valid){
        try{
            Connection connection = DBUtil.getConnection();

            PreparedStatement updateStatement = connection.prepareStatement(SET_CART_ITEM_ISVALID);

            updateStatement.setBoolean(1, valid );
            updateStatement.setString(2, username);
            updateStatement.setString(3, itemId);
            updateStatement.executeUpdate();

            DBUtil.closePreparedStatement(updateStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void clearCart(String username) {
        Cart cart = getCartByUsername(username);
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList = cart.getCartItemList();
        CartItem cartItem;
        for(int i = 0; i < cartItemList.size(); i ++){
            cartItem = cartItemList.get(i);
            updateItemValid(username, cartItem.getItem().getItemId(), false);
        }
    }

    @Override
    public boolean checkIfDbCartContainsItem(String itemId, String username) {
        if(catalogService == null){
            catalogService = new CatalogService();
        }

        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(CHECK_IF_DB_CONTAINS_PRODUCT_ID);
            pStatement.setString(1, username);
            pStatement.setString(2, itemId);
            ResultSet resultSet = pStatement.executeQuery();

            Boolean isContained = resultSet.next();

            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);

            return isContained;

        }catch (Exception e){
            e.printStackTrace();
        }



        return false;
    }

    @Override
    public void updateItemQuantity(String username, String itemId, int quantity) {
//        Cart cart = getCartByUsername(username);
        try{
            Connection connection = DBUtil.getConnection();
            Cart cart = getCartByUsername(username);
            if(cart == null){
                cart = new Cart();
            }

            PreparedStatement updateStatement = connection.prepareStatement(UPDATE_CART_ITEM_QUANTITY);
            cart.setQuantityByItemId(itemId, quantity);
            updateStatement.setInt(1, quantity );
            updateStatement.setString(2, username);
            updateStatement.setString(3, itemId);
            updateStatement.executeUpdate();

            DBUtil.closePreparedStatement(updateStatement);
            DBUtil.closeConnection(connection);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
