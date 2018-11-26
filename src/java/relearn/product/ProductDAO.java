/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import relearn.role.RoleDTO;
import relearn.user.UserDTO;
import relearn.utils.DBUtils;

/**
 *
 * @author nhatc
 */
public class ProductDAO {

    Connection conn = null;
    ResultSet rs = null;
    PreparedStatement stm = null;
    int result = 0;
    List<Product> products = null;

    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
    }

    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                String sql = "select p.id, p.name, p.price, p.type_id, p.is_sale, t.name as typeName from Product p, Type t where p.type_id = t.id";
                stm = conn.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int typeId = rs.getInt("type_id");
                    boolean isSale = rs.getBoolean("is_sale");
                    String typeName = rs.getString("typeName");
                    products.add(new Product(id, name, price, new TypeDTO(typeId, typeName), isSale));
                }
                return products;
            }
        } catch (Exception ex) {
            System.out.println("login-failed " + ex.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public boolean deleteProduct(int productId) {
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                String sql = "Delete from Product where id=?";
                stm = conn.prepareStatement(sql);
                stm.setInt(1, productId);
                result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return false;
    }

    public boolean updateProduct(int productId, String name,int productTypeId) {
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                String sql = "Update Product set name=?, type_id=? where id=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, name);
                  stm.setInt(2, productTypeId);
                stm.setInt(3, productId);
                result = stm.executeUpdate();
                if (result > 0) {
                    return true;
                }
                return false;
            }
        } catch (Exception ex) {

        } finally {
            closeConnection();
        }
        return false;
    }

    public List<Product> searchByName(String searchValue, int filterValue) {
        List<Product> products = filterByType(filterValue);
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toUpperCase().contains(searchValue.toUpperCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public List<Product> filterByType(int typeId) {
        products = new ArrayList<>();
        String sql = "select p.id, p.name, p.price, p.type_id, p.is_sale, t.name as typeName from Product p, Type t where p.type_id = t.id ";
        try {
            conn = DBUtils.MakeConnection();
            if (conn != null) {
                if (typeId > 0) {
                    sql = "select p.id, p.name, p.price, p.type_id, p.is_sale, t.name as typeName from Product p, Type t where p.type_id = t.id and p.type_id=?";
                }
                stm = conn.prepareStatement(sql);
                if (typeId > 0) {
                    stm.setInt(1, typeId);
                }
                rs = stm.executeQuery();
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    typeId = rs.getInt("type_id");
                    boolean isSale = rs.getBoolean("is_sale");
                    String typeName = rs.getString("typeName");
                    products.add(new Product(id, name, price, new TypeDTO(typeId, typeName), isSale));
                }
                return products;
            }
        } catch (Exception ex) {
            System.out.println("login-failed " + ex.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
}
