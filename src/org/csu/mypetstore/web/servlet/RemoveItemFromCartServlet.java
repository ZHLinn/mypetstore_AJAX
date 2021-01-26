package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class RemoveItemFromCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private static final String ERROR = "/WEB-INF/jsp/common/Error.jsp";

    private String workingItemId;
    private Cart cart;
    private Account account;
    private CartService cartService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart) session.getAttribute("cart");
        account = (Account) session.getAttribute("account");

        Item item = cart.removeItemById(workingItemId);

        if (item == null) {
            session.setAttribute("message", "Attempted to remove null CartItem from Cart.");
            request.getRequestDispatcher(ERROR).forward(request, response);
        } else {
            if(account!=null && account.isAuthenticated()){
                cartService = new CartService();
                cartService.removeItemFromCart(account.getUsername(), workingItemId);

                LogService logService = new LogService();
                String logDescription = "Remove Item, Item ID: " + workingItemId;
                Log log = new Log(account.getUsername(), Log.UPDATE_CART, logDescription);
                logService.insertLogInfo(log);


            }
            request.getRequestDispatcher(VIEW_CART).forward(request, response);
        }
    }
}
