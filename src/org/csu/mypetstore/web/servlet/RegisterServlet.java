package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterServlet extends HttpServlet {
    private static final String NEW_ACCOUNT_FORM = "/WEB-INF/jsp/account/NewAccountForm.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAOImpl();
        HttpSession session = request.getSession();

        String username = request.getParameter("username");

        String inputedPassword = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String city = request.getParameter("city");
        String state = request.getParameter("state");
        String zip = request.getParameter("zip");
        String country = request.getParameter("country");

        String languagePreference = request.getParameter("languagePreference");
        String favouriteCategoryId = request.getParameter("favouriteCategoryId");

        String[] listOption = request.getParameterValues("listOption");
        String[] bannerOption = request.getParameterValues("bannerOption");

        List<String> emptyItemList = new ArrayList<>();

        Account newAccount = new Account();
        newAccount.setAuthenticated(false);
        newAccount.setPassword(inputedPassword);

        String vrfCodeInput = request.getParameter("vrfCode");
        String theCode = (String)session.getAttribute("theCode");

        if( !username.isEmpty() ){
            newAccount.setUsername(username);
        }else{
            emptyItemList.add("Username");
        }
        if( !firstName.isEmpty() ){
            newAccount.setFirstName(firstName);
        }else{
            emptyItemList.add("FirstName");
        }
        if( !lastName.isEmpty() ){
            newAccount.setLastName(lastName);
        }else{
            emptyItemList.add("LastName");
        }
        if( !email.isEmpty() ){
            newAccount.setEmail(email);
        }else{
            emptyItemList.add("Email");
        }
        if( !phone.isEmpty() ){
            newAccount.setPhone(phone);
        }else{
            emptyItemList.add("Phone");
        }
        if( !address1.isEmpty() ){
            newAccount.setAddress1(address1);
        }else{
            emptyItemList.add("Address1");
        }
        if( !address2.isEmpty() ){
            newAccount.setAddress2(address2);
        }else{
            emptyItemList.add("Address2");
        }
        if( !city.isEmpty() ){
            newAccount.setCity(city);
        }else{
            emptyItemList.add("City");
        }
        if( !state.isEmpty() ){
            newAccount.setState(state);
        }else{
            emptyItemList.add("State");
        }
        if( !zip.isEmpty() ){
            newAccount.setZip(zip);
        }else{
            emptyItemList.add("Zip");
        }
        if( !country.isEmpty() ){
            newAccount.setCountry(country);
        }else{
            emptyItemList.add("Country");
        }
        if( !languagePreference.isEmpty() ){
            newAccount.setLanguagePreference(languagePreference);
        }
        if( !favouriteCategoryId.isEmpty() ){
            newAccount.setFavouriteCategoryId(favouriteCategoryId);
        }

        if( null == listOption ){
            newAccount.setListOption(false);
        }else{
            newAccount.setListOption(true);
        }

        if( null == bannerOption ){
            newAccount.setBannerOption(false);
        }else{
            newAccount.setBannerOption(true);
        }

        session.setAttribute("account", newAccount);

        //验证码是否正确
        if( null == vrfCodeInput || !vrfCodeInput.equalsIgnoreCase(theCode) ){
            request.setAttribute("registerMsg", "Invalid verification code. Register failed.");
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request,response);
            return;
        }

        //判断是否有同用户名的用户存在
        Account account = accountDAO.getAccountByUsername(username);
        if( null != account ){
            request.setAttribute("registerMsg", "username "+username+" has been occupied, please try again with another username");
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request, response);
        }

        boolean eq = inputedPassword.equals(repeatedPassword);
        if( !eq ){
            request.setAttribute("registerMsg", "The repeated password doesn't conform with the first one. Pleae try again." );
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request,response);
        }else if( inputedPassword.isEmpty() ){
            request.setAttribute("registerMsg", "Please enter your password." );
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request,response);
        }

        //查看是否有空项
        if(emptyItemList.size() == 0){
            accountDAO.insertSignon(newAccount);
            accountDAO.insertAccount(newAccount);
            accountDAO.insertProfile(newAccount);

            request.setAttribute("message", "You've registered successfully. Congratulations! \n You may sign in now.");
            request.getRequestDispatcher(SIGN_ON_FORM).forward(request,response);
        }else{
            StringBuilder message = new StringBuilder("Item(");
            StringBuilder itemListString = new StringBuilder();
            for (String s : emptyItemList) {
                itemListString.append(s).append(",");
            }
            //删除多余的","
            int len = itemListString.length();
            len -= 1;
            itemListString.deleteCharAt(len);
            message.append(itemListString);
            message.append(") is empty. Please fill them all and try again.");

            request.setAttribute("registerMsg", message.toString());
            request.getRequestDispatcher(NEW_ACCOUNT_FORM).forward(request,response);
        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
