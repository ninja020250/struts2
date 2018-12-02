/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import err.DashBoardError;
import java.util.Map;
import training.customer.CustomerDAO;
import training.customer.CustomerDTO;

/**
 *
 * @author nhatc
 */
public class DeleteAction extends ActionSupport {

    private final String SUCCESS = "success";
    private String lastSearchValue = "";
    private int customerId;
    private String message = "test thanh cong";

//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
    public DeleteAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return SUCCESS;
        }
        CustomerDAO customerDAO = new CustomerDAO();
        boolean result = customerDAO.removeCustomer(customerId);
        if (result) {
            Map application = (Map) ActionContext.getContext().get("application");
            application.put("test", "test thanh cong");
        }
//        Map request = (Map) ActionContext.getContext().get("request");
//        request.put("test", "test thanh cong");

        return SUCCESS;
    }

    public String getLastSearchValue() {
        return lastSearchValue;
    }

    public void setLastSearchValue(String lastSearchValue) {
        this.lastSearchValue = lastSearchValue;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

}
