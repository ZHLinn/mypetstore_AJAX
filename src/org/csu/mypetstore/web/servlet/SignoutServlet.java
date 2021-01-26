package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SignoutServlet extends HttpServlet {
    LogService logService;

    private static final String MAIN = "/WEB-INF/jsp/catalog/Main.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account loginAccount = (Account)session.getAttribute("account");
        loginAccount.setAuthenticated(false);

        session.setAttribute("account", null);

        logService = new LogService();
        Log log = new Log(loginAccount.getUsername(), Log.SIGN_OUT, " ");
        logService.insertLogInfo(log);

        request.getRequestDispatcher(MAIN).forward(request,response);

    }
}
