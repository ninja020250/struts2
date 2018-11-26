/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.Map;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts2.ServletActionContext;
import relearn.authen.AuthenticationDAO;
import relearn.user.UserDTO;

/**
 *
 * @author nhatc
 */
public class LoginAction extends ActionSupport {
    private String username;
    private String password;
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    protected HttpServletResponse servletResponse;
    public LoginAction() {
       
    }
    
    public String execute() throws Exception {
        AuthenticationDAO authenticationDAO = new AuthenticationDAO();
        UserDTO user = authenticationDAO.checkLogin(username, password);
         if(user != null){
             Map session = ActionContext.getContext().getSession();
              session.put("USER", user);
              Cookie userCookie = new Cookie(username, password);
              userCookie.setMaxAge(60*3);
             HttpServletResponse response = ServletActionContext.getResponse();
             response.addCookie(userCookie);
            return SUCCESS;
        }else{
             return FAIL;
         }
    }

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

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }
    
    
}
