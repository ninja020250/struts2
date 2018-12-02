/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import training.customer.CustomerDTO;

/**
 *
 * @author nhatc
 */
public class FilterAction extends ActionSupport {

    private int filterValue = -1;
    private String lastSearchValue = "";
    private boolean isFilter = true;

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public FilterAction() {
    }

    public int getFilterValue() {
        return filterValue;
    }

    public void setFilterValue(int filterValue) {
        this.filterValue = filterValue;
    }

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return SUCCESS;
        }
        session.put("CURRENT_FILTER", filterValue);
        return "success";
    }

}
