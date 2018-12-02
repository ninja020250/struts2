/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training.role;

/**
 *
 * @author nhatc
 */
public class RoleDTO {

    private int id;
    private String name;
    private int totalProduct;

    public RoleDTO() {
    }

    public RoleDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleDTO(int id, String name, int totalProduct) {
        this.id = id;
        this.name = name;
        this.totalProduct = totalProduct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalProduct() {
        return totalProduct;
    }

    public void setTotalProduct(int totalProduct) {
        this.totalProduct = totalProduct;
    }

}
