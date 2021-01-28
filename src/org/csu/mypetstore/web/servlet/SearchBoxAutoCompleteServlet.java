package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class SearchBoxAutoCompleteServlet extends HttpServlet {
    private CatalogService catalogService;
    private String keyword;


    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        catalogService = new CatalogService();
        keyword = request.getParameter( "keyword" );
        List<Product> productList = catalogService.searchProductList( keyword );
        String productListString = null;
        if ( !productList.isEmpty() ) {
            productListString = productList.get( 0 ).getName();
            if ( productList.size() > 1 ) {
                for ( int i = 1; i < productList.size(); i++ ) {
                    productListString.concat( "," + productList.get( i ) ); //Product类的toString()默认返回对象的name字
                }
            }
        }

        request.getRequestDispatcher( "/WEB-INF/jsp/catalog/Main.jsp" ).forward( request, response );

//        response.setContentType( "text/plain" );
//        PrintWriter out = response.getWriter();
//
//        out.print( productListString );
//
////        System.out.println("Product Array Generated Successfully");
//
//        out.flush();
//        out.close();
    }
}
