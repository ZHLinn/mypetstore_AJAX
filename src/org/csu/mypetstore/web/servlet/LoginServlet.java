package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Cart;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.persistence.AccountDAO;
import org.csu.mypetstore.persistence.impl.AccountDAOImpl;
import org.csu.mypetstore.service.AccountService;
import org.csu.mypetstore.service.CartService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";
    private static final String SIGN_ON_FORM = "/WEB-INF/jsp/account/SignonForm.jsp";

    private CartService cartService;
    private AccountService accountService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountDAO accountDAO = new AccountDAOImpl();
        accountService = new AccountService();
        cartService = new CartService();
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Account account;
//        account = new Account();
//        account.setUsername(username);
//        account.setPassword(password);
        account = accountService.getAccount(username, password);

        HttpSession session = request.getSession();
//        String vrfCodeInput = request.getParameter("vrfCode");
//        String theCode = (String)session.getAttribute("theCode");
//        //验证码是否正确
//        if( null == vrfCodeInput || !vrfCodeInput.equalsIgnoreCase(theCode) ){
//            request.setAttribute("message", "Invalid verification code. Signon failed.");
//            request.getRequestDispatcher(SIGN_ON_FORM).forward(request,response);
//            return;
//        }

        //同步方式完成登录
//        if(account != null){
//            account.setAuthenticated(true);
//            session.setAttribute("account", account );
//            LogService logService = new LogService();
//            Log log = new Log(username, Log.LOG_IN, "Successfully");
//            logService.insertLogInfo(log);
//            request.getRequestDispatcher(MAIN).forward(request,response);
//        }else{
//            request.setAttribute("message", "Invalid username or password. Signon failed.");
//            request.getRequestDispatcher(SIGN_ON_FORM).forward(request,response);
//            LogService logService = new LogService();
//            Log log = new Log(username, Log.LOG_IN, "failed");
//            logService.insertLogInfo(log);
//        }

        PrintWriter out = response.getWriter();
        String message;

        //异步方式完成登录
        if(account != null){
            account.setAuthenticated(true);
            session.setAttribute("account", account );

            LogService logService = new LogService();
            Log log = new Log(username, Log.LOG_IN, "Successfully");
            logService.insertLogInfo(log);


            message = "Log-in Succeed";
//
//            request.getRequestDispatcher(MAIN).forward(request,response);
        }else{
            request.setAttribute("message", "Invalid username or password. Signon failed.");
//            request.getRequestDispatcher(SIGN_ON_FORM).forward(request,response);
            session.setAttribute("account", null );
            LogService logService = new LogService();
            Log log = new Log(username, Log.LOG_IN, "failed");
            logService.insertLogInfo(log);

            message = "Log-in Failed";
        }

        out.print( message );
        out.flush();
        out.close();

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
