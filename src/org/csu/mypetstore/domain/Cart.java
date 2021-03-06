package org.csu.mypetstore.domain;

//import com.ibatis.common.util.PaginatedArrayList;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Cart implements Serializable {

    private static final long serialVersionUID = 8329559983943337176L;
    private final Map<String, CartItem> itemMap = Collections.synchronizedMap(new HashMap<String, CartItem>());
    private final List<CartItem> itemList = new ArrayList<CartItem>();

    public Iterator<CartItem> getCartItems() {
        return itemList.iterator();
    }

    public List<CartItem> getCartItemList() {
        return itemList;
    }

    public int getNumberOfItems() {
        return itemList.size();
    }

    public Iterator<CartItem> getAllCartItems() {
        return itemList.iterator();
    }

    public boolean containsItemId(String itemId) {
        return itemMap.containsKey(itemId);
    }

    public void addItem(Item item, boolean isInStock) {
        CartItem cartItem = (CartItem) itemMap.get(item.getItemId());
        if (cartItem == null) {
            cartItem = new CartItem();
            cartItem.setItem(item);
            if(item.getCartQuantity() == 0){
                cartItem.setCartQuantity( 0 );
            }else{
                cartItem.setCartQuantity(item.getCartQuantity()-1);
            }
            cartItem.setInStock(isInStock);
            itemMap.put(item.getItemId(), cartItem);
            itemList.add(cartItem);
        }
        cartItem.incrementCartQuantity();
    }

    public Item removeItemById(String itemId) {
        CartItem cartItem = (CartItem) itemMap.remove(itemId);
        if (cartItem == null) {
            return null;
        } else {
            itemList.remove(cartItem);
            return cartItem.getItem();
        }
    }

    public void incrementQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        cartItem.incrementCartQuantity();
    }

    public void setQuantityByItemId(String itemId, int quantity) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        if(cartItem != null){
            cartItem.setStockQuantity(quantity);
        }

    }

    public int getQuantityByItemId(String itemId) {
        CartItem cartItem = (CartItem) itemMap.get(itemId);
        return cartItem.getStockQuantity();
    }

    public BigDecimal getSubTotal() {
        BigDecimal subTotal = new BigDecimal("0");
        Iterator<CartItem> items = getAllCartItems();
        while (items.hasNext()) {
            CartItem cartItem = (CartItem) items.next();
            Item item = cartItem.getItem();
            BigDecimal listPrice = item.getListPrice();
            BigDecimal quantity = new BigDecimal(String.valueOf(cartItem.getStockQuantity()));
            subTotal = subTotal.add(listPrice.multiply(quantity));
        }
        return subTotal;
    }

}
