/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.cart;

import java.util.HashMap;
import java.util.Map;
import training.product.ProductDTO;

/**
 *
 * @author nhatc
 */
public class Cart {

    int customerId;
    Map<String, Integer> items = null;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Map<String, Integer> getItems() {
        return items;
    }

    public void setItems(Map<String, Integer> items) {
        this.items = items;
    }

    public Cart() {
    }

    public Cart(int customerId) {
        this.customerId = customerId;
    }

    public void addItem(String item) {
        if (items == null) {
            this.items = new HashMap<>();
        }
        int quantity = 1;
        if (items.containsKey(item)) {
            quantity = items.get(item) + 1;
        } else {
            items.put(item, quantity);
        }
    }

    public void removeItem(String item) {
        if (items == null) {
            this.items = new HashMap<>();
        }
        if (items != null) {
            if (items.containsKey(item)) {
                items.remove(item);
            }
            if (items.isEmpty()) {
                items = null;
            }
        }

    }

}
