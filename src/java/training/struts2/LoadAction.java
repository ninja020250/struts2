/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.HashMap;
import java.util.Map;
import training.customer.CustomerDTO;
import training.role.RoleDAO;

/**
 *
 * @author nhatc
 */
public class LoadAction extends ActionSupport {

    private final String SUCCESS = "success";

    public LoadAction() {
    }

    public String execute() throws Exception {
         Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return SUCCESS;
        }
        session.put("CURRENT_FILTER", -1);
        return SUCCESS;
    }

}
