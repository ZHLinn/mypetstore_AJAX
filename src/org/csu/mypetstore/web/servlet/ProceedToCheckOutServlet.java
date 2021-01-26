package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProceedToCheckOutServlet extends HttpServlet {
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";
    private static final String NEW_ORDER_FORM = "/WEB-INF/jsp/order/NewOrderForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account account = (Account)session.getAttribute("account");

        Cart cart = (Cart)session.getAttribute("cart");
        if( null == account || !account.isAuthenticated() ){
            request.setAttribute("message", "You must sign on before attempting to check out. Please sign on and try checking out again.");
            request.getRequestDispatcher(SIGN_ON_FORM).forward(request, response);
        }else{
            Order order = new Order();
            order.initOrder(account, cart);
            session.setAttribute("order", order);
            request.getRequestDispatcher(NEW_ORDER_FORM).forward(request,response);
        }


//        //填写默认银行卡信息
//        order.setCardType("Visa");
//        order.setCreditCard("999 9999 9999 9999");
//        order.setExpiryDate("12/03");
//        //根据账户信息填充默认的账单信息
//        order.setBillToFirstName(account.getFirstName());
//        order.setBillToLastName(account.getLastName());
//        order.setBillAddress1(account.getAddress1());
//        order.setBillAddress2(account.getAddress2());
//        order.setBillCity(account.getCity());
//        order.setBillState(account.getState());
//        order.setBillZip(account.getZip());
//        order.setBillCountry(account.getCountry());
//        //默认将bill地址作为ship地址
//        order.setShipToFirstName(order.getBillToFirstName());
//        order.setShipToLastName(order.getBillToLastName());
//        order.setShipAddress1(order.getBillAddress1());
//        order.setShipAddress2(order.getBillAddress2());
//        order.setShipCity(order.getBillCity());
//        order.setShipState(order.getBillState());
//        order.setShipZip(order.getBillZip());
//        order.setShipCountry(order.getBillCountry());

//        //将购物车信息转变为订单信息
//        List<CartItem> cartItemList = cart.getCartItemList();
//        for(int i = 0; i < cartItemList.size(); i ++){
//            if(cartItemList.get(i).isInStock()){
//                //检测是否有库存
//            }
//        }
//
//        List<LineItem> lineItems = new ArrayList<LineItem>();
//        int orderId = 1;
//        for(int i = 0; i < cartItemList.size(); i ++){
//            CartItem cartItem = cartItemList.get(i);
//            LineItem lineItem = new LineItem();
//            lineItem.setOrderId(orderId);
//            lineItem.setLineNumber(i);
//            lineItem.setQuantity(cartItem.getQuantity());
//            lineItem.setItemId(cartItem.getItem().getItemId());
//            lineItem.setUnitPrice(cartItem.getItem().getUnitCost());
//            lineItem.setItem(cartItem.getItem());
//            lineItem.setTotal(lineItem.getUnitPrice().multiply(new BigDecimal(lineItem.getQuantity())));
//            lineItems.add(lineItem);
//        }
//        order.setLineItems(lineItems);




    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
