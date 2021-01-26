package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Item;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddItemToCartServlet extends HttpServlet {

    //1.处理完请求后的跳转页面
    private static final String VIEW_CART = "/WEB-INF/jsp/cart/Cart.jsp";

    //2.定义处理该请求所需要的数据
    private String workingItemId;
    private Cart cart;
    private Account account;

    private CartService cartService;

    //3.是否需要调用业务逻辑层
    private CatalogService catalogService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cartService = new CartService();
        workingItemId = request.getParameter("workingItemId");

        HttpSession session = request.getSession();
        cart = (Cart)session.getAttribute("cart");
        account = (Account)session.getAttribute("account");

        if (cart == null) {
            cart = new Cart();
        }



        if (cart.containsItemId(workingItemId)) {
            cart.incrementQuantityByItemId(workingItemId);
        } else {
            catalogService = new CatalogService();
            boolean isInStock = catalogService.isItemInStock(workingItemId);
            Item item = catalogService.getItem(workingItemId);
            cart.addItem(item, isInStock);
        }
        session.setAttribute("cart", cart);




        if(account!=null && account.isAuthenticated()){
            LogService logService = new LogService();
            String logDescription = "Add item, Item ID: " + workingItemId;
            Log log = new Log(account.getUsername(), Log.UPDATE_CART, logDescription);
            logService.insertLogInfo(log);

            cartService.addItemToCart(workingItemId, account.getUsername());
        }

        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }
}
