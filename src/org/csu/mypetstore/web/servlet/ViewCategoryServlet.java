package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Account;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Log;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;
import org.csu.mypetstore.service.LogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewCategoryServlet extends HttpServlet {
    private static final String VIEW_CATEGORY = "/WEB-INF/jsp/catalog/Category.jsp";

    private String categoryId;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        categoryId = request.getParameter("categoryId");
        CatalogService service = new CatalogService();
        Category category = service.getCategory(categoryId);
        List<Product> productList = service.getProductListByCategory(categoryId);

        //将获取的信息放在作用域中
        HttpSession session = request.getSession();
        session.setAttribute("category", category);
        session.setAttribute("productList", productList );

        Account account = (Account)session.getAttribute("account");
        if(account!=null && account.isAuthenticated()){
            LogService logService = new LogService();
            String logDescription = "View Category, Category ID: " + categoryId;
            Log log = new Log(account.getUsername(), Log.VIEW, logDescription);
            logService.insertLogInfo(log);
        }

        request.getRequestDispatcher(VIEW_CATEGORY).forward(request,response);
    }
}
