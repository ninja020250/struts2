/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import training.customer.CustomerDAO;
import training.customer.CustomerDTO;
import training.role.RoleDAO;

/**
 *
 * @author nhatc
 */
public class LoginAction extends ActionSupport {

    private final String SUCCESS_ADMIN = "admin";
    private final String SUCCESS_STUDENT = "student";
    private final String FAIL = "fail";
    private String username = "";
    private String password = "";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginAction() {
    }

    public String execute() throws Exception {
        CustomerDAO customerDAO = new CustomerDAO();
        CustomerDTO customer = customerDAO.login(username, password);
        if (customer != null) {
            RoleDAO roleDAO = new RoleDAO();
            Map session = ActionContext.getContext().getSession();
            session.put("CUSTOMER", customer);
            if (customer.getRole().getName().equalsIgnoreCase("admin")) {
                return SUCCESS_ADMIN;
            } else {
                return SUCCESS_STUDENT;
            }

        }
        return FAIL;
    }

}
