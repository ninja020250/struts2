/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import training.customer.CustomerDTO;
import utils.DBUtil;

/**
 *
 * @author nhatc
 */
public class ProductDAO {

    Connection connection = null;
    ResultSet resultSet = null;
    PreparedStatement preparedStatement = null;
    List<ProductDTO> products = null;

    private void closeConnection() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("some things happen prevent close connection");
        } finally {
        }
    }

    public List<ProductDTO> getProducts() {
        try {
            products = new ArrayList<ProductDTO>();
            connection = DBUtil.makeDBConnection();
            if (connection != null) {
                String sql = "Select id, name, price from producttbl";
                preparedStatement = connection.prepareStatement(sql);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    float price = resultSet.getFloat("price");
                    ProductDTO product =  new ProductDTO(id, name, price);
                    products.add(product);
                }
                return products;
            }
        } catch (Exception e) {
        } finally {
            closeConnection();
        }
        return null;
    }

}
