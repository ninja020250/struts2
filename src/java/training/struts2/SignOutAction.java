/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;

/**
 *
 * @author nhatc
 */
public class SignOutAction extends ActionSupport {

    public SignOutAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        session.clear();
        return "success";
    }

}
