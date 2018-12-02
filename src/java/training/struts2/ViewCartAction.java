/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.Map;
import training.cart.Cart;
import training.customer.CustomerDTO;

/**
 *
 * @author nhatc
 */
public class ViewCartAction extends ActionSupport {

    private final String UNAUTHEN = "unAuthen";
    private Cart cart = null;

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public ViewCartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return UNAUTHEN;
        }
        cart = (Cart) ActionContext.getContext().getSession().get("CART");
        return "success";
    }

}
