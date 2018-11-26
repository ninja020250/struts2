<%-- 
    Document   : DashBoard
    Created on : Nov 21, 2018, 3:05:17 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>DashBoard</title>
        <link rel="stylesheet" href="assets/css/dashboard.css"/>
    </head>
    <body>
        <script src="assets/js/dashboard.js"></script>
        <h1>Hello ${sessionScope.USER.username} </h1>
        <s:set var="currentFilter" value="%{currentFilter}"></s:set>
            <div class="search-form">
                <form method="GET" action="search">
                    <label>Search: </label> <s:textfield name="searchValue" />
                <input type="submit" value="Search"/>
                <input type="hidden"  name="currentFilter" value="<s:property value="#currentFilter"/>"/>
            </form>
        </div>
        <div class="items-filter">
            <ul>
                <div class="item-type active" onClick="filterItem(event)">
                    <s:url var="filter" value="filter" >
                        <s:param name="currentFilter" value="-1" />
                        <s:param name="lastSearchValue" value="searchValue" />
                    </s:url>
                    <s:a href="%{filter}" >All(${numberAllProduct})</s:a>
                    </div>
                <s:iterator var="type" value="listType">
                    <div class="item-type" onClick="filterItem(event)"> 
                        <s:url var="filter" value="filter" >
                            <s:param name="currentFilter" value="%{#type.id}" />
                            <s:param name="lastSearchValue" value="%{searchValue}" />
                        </s:url>
                        <s:a href="%{filter}"><s:property value="%{#type.name}"/>(${type.number})</s:a>
                        </div>
                </s:iterator>
            </ul>
        </div>
        <s:if test="%{products != null and products.size() > 0}">
            <table border="1">
                <thead>
                    <tr>
                        <th>STT</th>
                        <th>ID</th>
                        <th>NAME</th>
                        <th>PRICE</th>
                        <th>IS_SALE</th>
                        <th>TYPE</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>
                    <s:iterator var="product" value="products" status="counter">
                        <s:form action="updateRecord">
                                            <tr >
                                <td><s:property value="%{#counter.count}"/></td> 
                                <td>
                                    <s:property value="%{#product.id}"/>
                                    <s:hidden name="id" value="%{#product.id}"/>
                                </td>
                                <td>
                                    <input name="name" value="${product.name}"/>
                                </td>
                                <td><s:property value="%{#product.price}"/></td>
                                <td><s:property value="%{#product.isSale}"/></td>
                                <td>
                                    <s:select name="productTypeId" list="itemList" 
                                              value="%{#product.type.id}"
                                               theme="simple"/>
                                </td>
                                <td>
                                    <s:url var="delete" value="delete" >
                                        <s:param name="productId" value="%{#product.id}" />
                                        <s:param name="currentFilter" value="%{currentFilter}" />
                                        <s:param name="lastSearchValue" value="searchValue" />
                                    </s:url>
                                    <s:a href="%{delete}" >DELETE</s:a>
                                    </td>
                                    <td>
                                        <button type="submit" >Update</button>
                                    </td>
                                <s:hidden name="lastSearchValue" value="%{searchValue}"/>
                                <s:hidden name="isFilter" value="%{isFilter}"/>
                                <s:hidden name="currentFilter" value="%{currentFilter}" />
                            </tr>
                        </s:form>
                    </s:iterator>
                </tbody>
            </table>
        </s:if>
        <s:if test="%{products == null or products.size() <= 0}">
            khong co du lieu
        </s:if>


    </body>
</html>
