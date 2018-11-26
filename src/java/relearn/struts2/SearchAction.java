/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package relearn.struts2;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.util.logging.Logger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import relearn.product.Product;
import relearn.product.ProductDAO;
import relearn.product.TypeDAO;
import relearn.product.TypeDTO;

/**
 *
 * @author nhatc
 */
public class SearchAction extends ActionSupport {

    private String searchValue = "";
    private final String SUCCESS = "success";
    private final String FAIL = "fail";
    List<Product> products = null;
    private int currentFilter = -1;
    private List<TypeDTO> listType = null;
    private boolean isFilter = false;
    private int numberAllProduct = 0;
    private Map<String, String> itemList =  new HashMap<>();

    public Map<String, String> getItemList() {
        return itemList;
    }

    public void setItemList(Map<String, String> itemList) {
        this.itemList = itemList;
    }

    public int getNumberAllProduct() {
        return numberAllProduct;
    }

    public void setNumberAllProduct(int numberAllProduct) {
        this.numberAllProduct = numberAllProduct;
    }

    public boolean isIsFilter() {
        return isFilter;
    }

    public void setIsFilter(boolean isFilter) {
        this.isFilter = isFilter;
    }

    public List<TypeDTO> getListType() {
        return listType;
    }

    public void setListType(List<TypeDTO> listType) {
        this.listType = listType;
    }

    public int getCurrentFilter() {
        return currentFilter;
    }

    public void setCurrentFilter(int currentFilter) {
        this.currentFilter = currentFilter;
    }

    public SearchAction() {
    }

    public String execute() throws Exception {
        ProductDAO productDAO = new ProductDAO();
        if (isFilter) {
            products = productDAO.searchByName("", currentFilter);
        } else {
            products = productDAO.searchByName(searchValue, currentFilter);
        }
        TypeDAO dao = new TypeDAO();
        listType = dao.getListType();
        for (TypeDTO dto : listType) {
            numberAllProduct += dto.getNumber();
            itemList.put(String.valueOf(dto.getId()), dto.getName());
        }
        
        //HttpServletRequest request = ServletActionContext.getRequest();
        //  request.setAttribute("PRODUCTS", products);
        return SUCCESS;
    }

    public String getSearchValue() {
        return searchValue;
    }

    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public static Logger getLOG() {
        return LOG;
    }

    public static void setLOG(Logger LOG) {
        ActionSupport.LOG = LOG;
    }

}
