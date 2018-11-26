/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.List;
import relearn.product.Product;
import relearn.product.ProductDAO;

/**
 *
 * @author nhatc
 */
public class FilterAction extends ActionSupport {

    private String lastSearchValue = "";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private int currentFilter = -1;
    private boolean isFilter =  true;

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
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

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

    public FilterAction() {
    }

    public String execute() throws Exception {
        return SUCCESS;
    }

}
