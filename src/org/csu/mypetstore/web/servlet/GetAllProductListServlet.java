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

public class GetAllProductListServlet extends HttpServlet {
    private CatalogService catalogService;

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        catalogService = new CatalogService();
        List<Product> productList = catalogService.getAllProductList();
        StringBuilder productListString = null;
        if ( !productList.isEmpty() ) {
            productListString = new StringBuilder( productList.get( 0 ) + "" );
            if ( productList.size() > 1 ) {
                for ( int i = 1; i < productList.size(); i++ ) {
                    productListString.append( "," ).append( productList.get( i ) ) ; //Product类的toString()默认返回对象的name字
                }
            }
        }

        response.setContentType( "text/plain" );
        PrintWriter out = response.getWriter();
        out.print( productListString );
        out.flush();
        out.close();
    }
}
