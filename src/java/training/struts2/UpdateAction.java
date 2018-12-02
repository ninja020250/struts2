/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import training.customer.CustomerDAO;
import training.customer.CustomerDTO;

/**
 *
 * @author nhatc
 */
public class UpdateAction extends ActionSupport {

    private String lastSearchvalue = "";
    private String fullname;
    private int roleId;
    private int id;
    private final String UNAUTHEN = "unAuthen";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public UpdateAction() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return SUCCESS;
        }
        CustomerDTO currentCustomer = (CustomerDTO) ActionContext.getContext().getSession().get("CUSTOMER");
        CustomerDAO customerDAO = new CustomerDAO();
        boolean result = customerDAO.updateCustomer(id, fullname, roleId);
        if (result) {
            if (currentCustomer.getId() == id && (currentCustomer.getRole().getId() != roleId)) {
                return UNAUTHEN;
            }
        } else {
            return FAIL;
        }
        return SUCCESS;
    }

    public String getLastSearchvalue() {
        return lastSearchvalue;
    }

    public void setLastSearchvalue(String lastSearchvalue) {
        this.lastSearchvalue = lastSearchvalue;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

}
