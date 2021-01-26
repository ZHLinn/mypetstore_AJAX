package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.domain.Order;
import org.csu.mypetstore.persistence.CartDAO;
import org.csu.mypetstore.persistence.impl.CartDAOImpl;
import org.csu.mypetstore.service.LogService;
import org.csu.mypetstore.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SubmitOrderServlet extends HttpServlet {
    private Account account;
    private CartDAO cartDAO;

    private static final String VIEW_ORDER = "/WEB-INF/jsp/order/ViewOrder.jsp";
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        Order order = (Order) session.getAttribute("order");
        session.setAttribute("lineItems",order.getLineItems());
        account = (Account)session.getAttribute("account");

        OrderService orderService = new OrderService();
        orderService.insertOrder(order);
//        orderService.insertOrderStatus;

        // 重置购物车
        Cart cart = new Cart();
        session.setAttribute("cart",cart);
        cartDAO = new CartDAOImpl();
        cartDAO.clearCart(account.getUsername());


        session.setAttribute("orderMsg", "Thank you, your order has been submitted.");

        LogService logService = new LogService();
        String logDescription = "Order ID: " + order.getOrderId();
        Log log = new Log(account.getUsername(), Log.SUBMIT_ORDER, logDescription);
        logService.insertLogInfo(log);

        request.getRequestDispatcher(VIEW_ORDER).forward(request, response);
    }
}
