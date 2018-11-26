/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import relearn.product.ProductDAO;

/**
 *
 * @author nhatc
 */
public class DeleteAction extends ActionSupport {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int productId;
    private int currentFilter;
    private String lastSearchValue;

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(int currentFilter) {
        this.currentFilter = currentFilter;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public DeleteAction() {
    }

    public String execute() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        boolean result = productDAO.deleteProduct(productId);
        if (result) {
            return SUCCESS;
        } else {
            return FAIL;
        }
    }

}
