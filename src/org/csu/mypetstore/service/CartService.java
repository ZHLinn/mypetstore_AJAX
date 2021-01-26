package org.csu.mypetstore.service;

import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.impl.CartDAOImpl;

public class CartService {

    private CartDAO cartDAO;

    public CartService() {
        cartDAO = new CartDAOImpl();
    }

    public Cart getCartByUsername(String username){
        return cartDAO.getCartByUsername(username);
    }

    public void addItemToCart(String itemId, String username){
        cartDAO.addItemToCart(itemId, username);
    }

    public void removeItemFromCart(String username, String itemId){
        cartDAO.updateItemValid(username, itemId, false);
        cartDAO.updateItemQuantity(username, itemId, 0);
    }

    public void updateItemQuantity(String username, String itemId, int quantity){
        cartDAO.updateItemQuantity(username, itemId, quantity);
    }

    public void clearCart(String username){
        cartDAO.clearCart(username);
    }

}


