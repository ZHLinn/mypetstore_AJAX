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

public class ProcessOrderInfoServlet extends HttpServlet {
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Order order = (Order)session.getAttribute("order");

        //从NewOrderForm.jsp取值
        String cardType = request.getParameter("cardType");
        String creditCard = request.getParameter("creditCard");
        String expiryDate = request.getParameter("expiryDate");
        String billToFirstName = request.getParameter("billToFirstName");
        String billToLastName = request.getParameter("billToLastName");
        String billAddress1 = request.getParameter("billAddress1");
        String billAddress2 = request.getParameter("billAddress2");
        String billCity = request.getParameter("billCity");
        String billState = request.getParameter("billState");
        String billZip = request.getParameter("billZip");
        String billCountry = request.getParameter("billCountry");
        String[] shippingAddressRequired = request.getParameterValues("shippingAddressRequired");

        //将request中的order信息修改为用户填写的表单信息
        order.setCardType(cardType);
        order.setCreditCard(creditCard);
        order.setExpiryDate(expiryDate);
        order.setBillToFirstName(billToFirstName);
        order.setBillToLastName(billToLastName);
        order.setBillAddress1(billAddress1);
        order.setBillAddress2(billAddress2);
        order.setBillCity(billCity);
        order.setBillState(billState);
        order.setBillZip(billZip);
        order.setBillCountry(billCountry);

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        order.setOrderDate(date);

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

        formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        date = new Date(System.currentTimeMillis());
        order.setOrderDate(date);

        request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);

    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doPost( request, response );
    }
}
