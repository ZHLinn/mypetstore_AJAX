package org.csu.mypetstore.persistence;

import org.csu.mypetstore.domain.*;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

public interface CartDAO {

    Cart getCartByUsername(String username);

    void addItemToCart(String itemId, String username);

    void updateItemQuantity(String username, String itemId, int quantity);

    void updateItemValid(String username, String itemId, boolean valid);

    void clearCart(String username);

    boolean checkIfDbCartContainsItem( String itemId, String username );
}
