/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.product;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author nhatc
 */
public class TypeDTO {
    private int id;
    private String name;
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public TypeDTO(int id, String name, int number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }
    
    public TypeDTO() {
    }

    public TypeDTO(int id, String name) {
        this.id = id;
        this.name = name;
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
    
}
