/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import relearn.authen.AuthenticationDAO;
import relearn.user.UserDTO;

/**
 *
 * @author nhatc
 */
public class InitAction extends ActionSupport {

    private final String SUCCESS = "success";
    private final String FAIL = "fail";

    public InitAction() {

    }

    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String username = cookie.getName();
                String password = cookie.getValue();
                AuthenticationDAO authenticationDAO = new AuthenticationDAO();
                UserDTO user = authenticationDAO.checkLogin(username, password);
                if (user != null) {
                    Map session = ActionContext.getContext().getSession();
                    session.put("USER", user);
                    return SUCCESS;
                } else {
                    return FAIL;
                }
            }

        }
        return FAIL;
    }

}
