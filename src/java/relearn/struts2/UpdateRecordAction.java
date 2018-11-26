/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionSupport;
import relearn.product.ProductDAO;

/**
 *
 * @author nhatc
 */
public class UpdateRecordAction extends ActionSupport {

    private String name;
    private int id;
    private String lastSearchValue = "";
    private int currentFilter = -1;
    private boolean isFilter = false;
    private int productTypeId;

    public int getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(int productTypeId) {
        this.productTypeId = productTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public int getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(int currentFilter) {
        this.currentFilter = currentFilter;
    }

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
    }

    public UpdateRecordAction() {
    }

    public String execute() throws Exception {
        ProductDAO dao = new ProductDAO();
        boolean result = dao.updateProduct(id, name, productTypeId);
        if (result) {
            return "success";
        } else {
            return "fail";
        }

    }

}
