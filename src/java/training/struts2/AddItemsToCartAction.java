/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.struts2;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.List;
import java.util.Map;
import training.cart.Cart;
import training.customer.CustomerDTO;

/**
 *
 * @author nhatc
 */
public class AddItemsToCartAction extends ActionSupport {

    private List<String> itemsBought = null;
    private final String SUCCESS = "success";

    public AddItemsToCartAction() {
    }

    public String execute() throws Exception {
        Map session = ActionContext.getContext().getSession();
        CustomerDTO currentUser = (CustomerDTO) session.get("CUSTOMER");
        if (currentUser == null) {
            return SUCCESS;
        }
        Cart cart = (Cart) session.get("CART");
        if (cart == null) {
            cart = new Cart(currentUser.getId());
        }
        for (int i = 0; i < itemsBought.size(); i++) {
            cart.addItem(itemsBought.get(i));
        }
        session.put("CART", cart);
        return SUCCESS;
    }

    public List<String> getItemsBought() {
        return itemsBought;
    }

    public void setItemsBought(List<String> itemsBought) {
        this.itemsBought = itemsBought;
    }

}
