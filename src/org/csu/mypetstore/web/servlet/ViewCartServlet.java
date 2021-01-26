package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class ViewCartServlet extends HttpServlet {
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";
    private CartService cartService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute("cart");
        Account account = (Account)session.getAttribute("account");
        cartService = new CartService();

        if (cart == null) {
            cart = new Cart();
            cart = cartService.getCartByUsername(account.getUsername());
            session.setAttribute("cart", cart);
        }

        if(account!=null && account.isAuthenticated()){
            LogService logService = new LogService();
            String logDescription = "View Cart";
            Log log = new Log(account.getUsername(), Log.VIEW, logDescription);
            logService.insertLogInfo(log);
        }
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
