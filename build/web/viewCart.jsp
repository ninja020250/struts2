<%-- 
    Document   : viewCart
    Created on : Nov 28, 2018, 3:43:08 PM
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
        <h1>Your cart (${sessionScope.CUSTOMER.fullname})</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>stt</th>
                    <th>name</th>
                    <th>quantity</th>
                </tr>
            </thead>
            <tbody>
                <s:iterator var="item" value="cart.items" status="Counter">
                    <tr>
                        <td><s:property value="%{#Counter.count}"/></td>
                        <td><s:property value="%{#item.key}"/></td>
                        <td><s:property value="%{#item.value}"/></td>
                    </tr>
                </s:iterator>

            </tbody>
        </table>

    </body>
</html>
