/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import err.DashBoardError;
import java.util.List;
import java.util.Map;
import training.customer.CustomerDAO;
import training.customer.CustomerDTO;
import training.role.RoleDAO;
import training.role.RoleDTO;

/**
 *
 * @author nhatc
 */
public class SearchAction extends ActionSupport {

    private final String UNAUTHEN = "unAuthen";
    private String searchValue = "";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    private List<CustomerDTO> customers = null;
    private List<RoleDTO> roles = null;
    private Map<String, String> mapRoles = null;
    private int currentFilter = -1;
    private boolean isFilter = false;
    private int numberAllCustomer = 0;
//    private String message = "";
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }

    public int getNumberAllCustomer() {
        return numberAllCustomer;
    }

    public void setNumberAllCustomer(int numberAllCustomer) {
        this.numberAllCustomer = numberAllCustomer;
    }

    public int getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(int currentFilter) {
        this.currentFilter = currentFilter;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public Map getMapRoles() {
        return mapRoles;
    }

    public void setMapRoles(Map mapRoles) {
        this.mapRoles = mapRoles;
    }

    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }

    public SearchAction() {
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
            return UNAUTHEN;
        }

        currentFilter = (int) session.get("CURRENT_FILTER");
        CustomerDAO customerDAO = new CustomerDAO();
        if (isFilter) {
            customers = customerDAO.searchByfullName("", currentFilter);
        } else {
            customers = customerDAO.searchByfullName(searchValue, currentFilter);
        }

        RoleDAO roleDAO = new RoleDAO();
        roles = roleDAO.getRoles();
        for (RoleDTO dto : roles) {
            numberAllCustomer += dto.getTotalProduct();
        }
        mapRoles = roleDAO.loadRoleAsMap();
//        Map request = (Map) ActionContext.getContext().get("request");
//        String temp = (String) request.get("test");
//        request.put("test", temp);
        Map application = (Map) ActionContext.getContext().get("application");
        Map request = (Map) ActionContext.getContext().get("request");
        request.put("test", application.get("test"));
        application.remove("test");
        return SUCCESS;

    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

}
