package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.CartItem;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

public class UpdateCartQuantitiesServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private Cart cart;
    private Account account;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        account = (Account)session.getAttribute("account");
        CartService cartService = new CartService();
        int quantity, originalQuantity;

        Iterator<CartItem> cartItems = cart.getAllCartItems();
        boolean isSthRemoved = false;
        StringBuilder removedItemsString = new StringBuilder("");
        while (cartItems.hasNext()) {
            CartItem cartItem = cartItems.next();
            String itemId = cartItem.getItem().getItemId();
            try {
                quantity = Integer.parseInt(request.getParameter(itemId));
                originalQuantity = cart.getQuantityByItemId(itemId);
                cart.setQuantityByItemId(itemId, quantity);
                if (quantity < 1) {
                    cartItems.remove();
                    removedItemsString.append( "," ).append( cartItem.getItem().getItemId() );
                    isSthRemoved = true;
                    if(account!=null && account.isAuthenticated()){
                        cartService.removeItemFromCart(account.getUsername(), itemId);

                        LogService logService = new LogService();
                        String logDescription = "Remove Item, Item ID: " + itemId;
                        Log log = new Log(account.getUsername(), Log.UPDATE_CART, logDescription);
                        logService.insertLogInfo(log);
                    }
                }else if(originalQuantity != quantity){
                    if(account!=null && account.isAuthenticated()){
                        cartService.updateItemQuantity(account.getUsername(), itemId, quantity);

                        LogService logService = new LogService();
                        String logDescription = "Change Quantity, Item ID: " + itemId + ", From " + originalQuantity + " to " + quantity;
                        Log log = new Log(account.getUsername(), Log.UPDATE_CART, logDescription);
                        logService.insertLogInfo(log);
                    }
                }
            } catch (Exception e) {
                session.setAttribute("message", "The Quantities of Item must be Integer!");
//                request.getRequestDispatcher(ERROR).forward(request, response);
                return;
            }
        }

        session.setAttribute( "cart", cart );

        StringBuilder message = new StringBuilder("");

        if( !isSthRemoved ){
            message.append( "No Remove Action" );
        }else{

            message.append( "Remove Action/" ); /*Append a '/' in the end for js to call split('/')*/
            if(cart.getNumberOfItems() == 0){
                message.append( "Empty" );
            }
            message.append( removedItemsString );
        }



        response.setContentType( "text/plain" );
        PrintWriter out = response.getWriter();
        out.write(message.toString());
        out.flush();
        out.close();

//        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
