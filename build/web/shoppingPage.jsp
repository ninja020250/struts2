<%-- 
    Document   : shoppingPage
    Created on : Nov 28, 2018, 2:40:33 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <script src="assets/js/shopping.js"></script>
        <h1 >Welcome to Shopping Page (${sessionScope.CUSTOMER.fullname})</h1>
        <s:if test="%{products != null}">
            <table border="1">
                <thead>
                    <tr>
                        <th><input type="checkbox" id="checkAll" value="checkAll" onClick="checkAll()"/></th>
                        <th>stt</th>
                        <th>id</th>
                        <th>name</th>
                        <th>price</th>
                    </tr>
                </thead>
                <tbody>
                    <s:form action="addItemsToCart" id="form-add-to-cart" theme="simple">
                        <s:iterator var="product" value="products" status="Counter">
                            <tr>
                                <td><input type="checkbox" name="itemsBought" value="${product.name}" class="checkItems"/></td>
                                <td><s:property value="%{#Counter.count}"/></td>
                                <td><s:property value="%{#product.id}"/></td>
                                <td><s:property value="%{#product.name}"/></td>
                                <td><s:property value="%{#product.price}"/></td>
                            </tr>
                        

                    </s:iterator>
                            <button onClick="addItemsToCart()" >Add to Cart</button>
                </s:form>
            </tbody>
        </table>

    </s:if>
    <s:else>
        <h2>  sorry, my store close today</h2>
    </s:else>
    <s:a href="viewCart">View Cart</s:a>
    <s:a href="signOut">Sign Out</s:a> 
</body>
</html>
