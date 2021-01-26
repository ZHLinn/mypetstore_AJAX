package org.csu.mypetstore.persistence.impl;
import org.csu.mypetstore.domain.Category;
import org.csu.mypetstore.domain.Product;
import org.csu.mypetstore.persistence.DBUtil;
import org.csu.mypetstore.persistence.ProductDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductImpl implements ProductDAO {
    private static final String GET_PRODUCT_LIST_BY_CATEGORY = "SELECT PRODUCTID, CATEGORY, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE CATEGORY = ?";
    private static final String GET_PRODUCT = "SELECT PRODUCTID, CATEGORY, NAME, DESCN as description, CATEGORY as categoryId FROM PRODUCT WHERE PRODUCTID = ?";
    private static final String SEARCH_PRODUCT_LIST = "SELECT PRODUCTID, CATEGORY, NAME, DESCN as description, CATEGORY as categoryId from PRODUCT WHERE lower(name) like ?";

    @Override
    public List<Product> getProductListByCategory(String categoryId) {
        List<Product> products = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT_LIST_BY_CATEGORY);
            pStatement.setString(1, categoryId );
            ResultSet resultSet = pStatement.executeQuery();
            while( resultSet.next() ){
                Product product = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
//                System.out.println(product.getCategoryId()+" "+product.getName());
                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getProduct(String productId) {
        Product product = null;
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(GET_PRODUCT);
            pStatement.setString(1, productId );
            ResultSet resultSet = pStatement.executeQuery();
            if( resultSet.next() ){
                product = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return product;
    }

    @Override
    public List<Product> searchProductList(String keywords) {
        List<Product> products = new ArrayList<>();
        try{
            Connection connection = DBUtil.getConnection();
            PreparedStatement pStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
            pStatement.setString(1, keywords );
            ResultSet resultSet = pStatement.executeQuery();
            while( resultSet.next() ){
                Product product = new Product(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4)
                );
                products.add(product);
            }
            DBUtil.closeResultSet(resultSet);
            DBUtil.closePreparedStatement(pStatement);
            DBUtil.closeConnection(connection);
        }catch (Exception e){
            e.printStackTrace();
        }

        return products;
    }
//
//    //测试setString语句
//    public static void main(String[] args) throws Exception{
//        Connection connection = DBUtil.getConnection();
//        PreparedStatement pStatement = connection.prepareStatement(SEARCH_PRODUCT_LIST);
//        pStatement.setString(1, "keyword" );
//
//        System.out.println(pStatement);
//    }
}
