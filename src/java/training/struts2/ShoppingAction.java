/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import training.customer.CustomerDTO;
import training.product.ProductDAO;
import training.product.ProductDTO;

/**
 *
 * @author nhatc
 */
public class ShoppingAction extends ActionSupport {

    private final String UNAUTHEN = "unAuthen";
    private List<ProductDTO> products = null;
    private final String SUCCESS = "success";

    public ShoppingAction() {
    }

    public String execute() throws Exception {
        Map session  =  ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO)session.get("CUSTOMER");
        if (currentUser == null) {
            return UNAUTHEN;
        }
        ProductDAO productDAO = new ProductDAO();
        products = productDAO.getProducts();
        return SUCCESS;
    }

    public List<ProductDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductDTO> products) {
        this.products = products;
    }

}
