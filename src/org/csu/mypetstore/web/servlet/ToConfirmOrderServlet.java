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

public class ToConfirmOrderServlet extends HttpServlet {
    private static final String SHIPPING_FORM = "/WEB-INF/jsp/order/ShippingForm.jsp";
    private static final String CONFIRM_ORDER = "/WEB-INF/jsp/order/ConfirmOrder.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        if(null == shippingAddressRequired){
            //默认将bill地址作为ship地址
            order.setShipToFirstName(order.getBillToFirstName());
            order.setShipToLastName(order.getBillToLastName());
            order.setShipAddress1(order.getBillAddress1());
            order.setShipAddress2(order.getBillAddress2());
            order.setShipCity(order.getBillCity());
            order.setShipState(order.getBillState());
            order.setShipZip(order.getBillZip());
            order.setShipCountry(order.getBillCountry());
        }else{
            request.getRequestDispatcher(SHIPPING_FORM).forward(request, response);
            return;
        }

        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        order.setOrderDate(date);

        request.getRequestDispatcher(CONFIRM_ORDER).forward(request,response);


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
