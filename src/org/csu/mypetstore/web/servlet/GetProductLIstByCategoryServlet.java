package org.csu.mypetstore.web.servlet;

import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.ProductDAO;
import org.csu.mypetstore.persistence.impl.ProductImpl;
import org.csu.mypetstore.service.CatalogService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class GetProductLIstByCategoryServlet extends HttpServlet {
    private CatalogService catalogService;

    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        doGet( request, response );
    }

    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        catalogService = new CatalogService();

        String categoryId = request.getParameter( "categoryId" );
        List<Product> productList = catalogService.getProductListByCategory( categoryId );
        StringBuilder respText = new StringBuilder();

        if ( productList != null ){
            respText.append( "Success;" );

            for(int i = 0; i < productList.size(); i ++ ){
                if( i == 0 ){
                    respText.append( productList.get( i ).getName() );
                }else{
                    respText.append( "," ).append( productList.get( i ).getName() );
                }
            }
        }else{
            respText.append( "Failure;" );
        }

        response.setContentType( "text/plain" );
        PrintWriter out = response.getWriter();
        out.print( respText );
        out.flush();
        out.close();
    }
}
