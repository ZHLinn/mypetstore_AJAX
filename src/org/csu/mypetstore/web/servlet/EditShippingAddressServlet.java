package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Order;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EditShippingAddressServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");

        String shipToFirstName = request.getParameter("shipToFirstName");
        String shipToLastName = request.getParameter("shipToLastName");
        String shipAddress1 = request.getParameter("shipAddress1");
        String shipAddress2 = request.getParameter("shipAddress2");
        String shipCity = request.getParameter("shipCity");
        String shipState = request.getParameter("shipState");
        String shipZip = request.getParameter("shipZip");
        String shipCountry = request.getParameter("shipCountry");

        order.setShipToFirstName(shipToFirstName);
        order.setShipToLastName(shipToLastName);
        order.setShipAddress1(shipAddress1);
        order.setShipAddress2(shipAddress2);
        order.setShipCity(shipCity);
        order.setShipState(shipState);
        order.setShipZip(shipZip);
        order.setShipCountry(shipCountry);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        order.setOrderDate(date);

        request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
