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

public class SaveAccontInfoServlet extends HttpServlet {
    private static final String EDIT_ACCOUNT_FORM = "/WEB-INF/jsp/account/EditAccountForm.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        request.setCharacterEncoding("GB2312");

        boolean eq = true;
        AccountDAO accountDAO = new AccountDAOImpl();
        HttpSession session = request.getSession();
        Account loginAccount = (Account) session.getAttribute("account");

        String inputedPassword = request.getParameter("password");
        String repeatedPassword = request.getParameter("repeatedPassword");
        eq = inputedPassword.equals(repeatedPassword);

        if(eq){//两次输入一致，可能是修改密码，也可能是不修改密码

            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address1 = new String(request.getParameter("address1").getBytes("iso-8859-1"), "utf-8");

//            String address1 = request.getParameter("address1");
            String address2 = request.getParameter("address2");
            String city = request.getParameter("city");
            String state = request.getParameter("state");
            String zip = request.getParameter("zip");
            String country = request.getParameter("country");

            String languagePreference = request.getParameter("languagePreference");
            String favouriteCategoryId = request.getParameter("favouriteCategoryId");

            String[] listOption = request.getParameterValues("listOption");
            String[] bannerOption = request.getParameterValues("bannerOption");

            if( !inputedPassword.isEmpty() ){
                loginAccount.setPassword(inputedPassword);
            }
            if( !firstName.isEmpty() ){
                loginAccount.setFirstName(firstName);
            }
            if( !lastName.isEmpty() ){
                loginAccount.setLastName(lastName);
            }
            if( !email.isEmpty() ){
                loginAccount.setEmail(email);
            }
            if( !phone.isEmpty() ){
                loginAccount.setPhone(phone);
            }
            if( !address1.isEmpty() ){
                loginAccount.setAddress1(address1);
            }
            if( !address2.isEmpty() ){
                loginAccount.setAddress2(address2);
            }
            if( !city.isEmpty() ){
                loginAccount.setCity(city);
            }
            if( !state.isEmpty() ){
                loginAccount.setState(state);
            }
            if( !zip.isEmpty() ){
                loginAccount.setZip(zip);
            }
            if( !country.isEmpty() ){
                loginAccount.setCountry(country);
            }
            if( !languagePreference.isEmpty() ){
                loginAccount.setLanguagePreference(languagePreference);
            }
            if( !favouriteCategoryId.isEmpty() ){
                loginAccount.setFavouriteCategoryId(favouriteCategoryId);
            }

            if( null == listOption ){
                loginAccount.setListOption(false);
            }else{
                loginAccount.setListOption(true);
            }

            if( null == bannerOption ){
                loginAccount.setBannerOption(false);
            }else{
                loginAccount.setBannerOption(true);
            }

            accountDAO.updateAccount(loginAccount);
            if(!inputedPassword.isEmpty()){
                accountDAO.updateSignon(loginAccount);
            }

            accountDAO.updateProfile(loginAccount);

            request.setAttribute("saveInfoMsg", "Infomation saved successfully");
            request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
        }else{
            request.setAttribute("saveInfoMsg", "The repeated password doesn't conform with the first one. Pleae try again.");
            request.getRequestDispatcher(EDIT_ACCOUNT_FORM).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
