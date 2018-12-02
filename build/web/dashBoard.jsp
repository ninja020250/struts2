<%-- 
    Document   : dashboard
    Created on : Nov 27, 2018, 2:29:30 PM
    Author     : nhatc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .message{
                color: red;
            }
        </style>
    </head>
    <body>
        <h1 class="message" >
        <s:property value="#request.test" />
        </h1>
        <s:property value="%{#request.DASHBOARD_ERROR.deleteError}"/>
        <h1>welcome ${sessionScope.CUSTOMER.fullname}</h1>
        <div class="form-group">
            <form action="search">
                <s:textfield name="searchValue" value="%{searchValue}"/>
                <button value="Search" type="submit">Search</button>
            </form>
        </div>
        <div class="list-filter">
            <ul>
                <s:url var="filter" value="filter">
                    <s:param name="filterValue" value="-1"/>
                    <s:param name="lastSearchValue" value="%{searchValue}"/>
                </s:url>
                <s:a href="%{filter}">All(${numberAllCustomer})</s:a>
                <s:iterator var="role" value="roles">
                    <s:url var="filter" value="filter">
                        <s:param name="filterValue" value="%{#role.id}"/>
                        <s:param name="lastSearchValue" value="%{searchValue}"/>
                    </s:url>
                    <s:a href="%{filter}">${role.name}(${role.totalProduct})</s:a>
                </s:iterator>
            </ul>
        </div>
        <div class="search-result">
            <s:if test="%{customers != null and customers.size() > 0}">
                <table border="1">
                    <thead>
                        <tr>
                            <th>stt</th>
                            <th>id</th>
                            <th>username</th>
                            <th>role</th>
                            <th>full name</th>
                            <th>Delete</th>
                            <th>Update</th>
                        </tr>
                    </thead>
                    <tbody>
                        <s:iterator var="customer" value="customers" status="Couter">
                            <s:form theme="simple" action="update">
                                <tr>
                                    <td>
                                        <s:property value="%{#Couter.count}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#customer.id}"/>
                                        <s:hidden name="id" value="%{#customer.id}"/>
                                    </td>
                                    <td>
                                        <s:property value="%{#customer.username}"/>
                                    </td>
                                    <td>
                                        <s:select name="roleId" 
                                                  list="mapRoles"
                                                  value="%{#customer.role.id}"
                                                  theme="simple"/>
                                    </td>
                                    <td>
                                        <s:textfield name="fullname" value="%{#customer.fullname}"/>
                                    </td>
                                    <td>
                                        <s:url var="del" value="delete">
                                            <s:param name="lastSearchValue" value="%{searchValue}"/>
                                            <s:param name="customerId" value="%{#customer.id}"/>
                                        </s:url>
                                        <s:a href="%{del}">Delete</s:a>
                                        </td>
                                        <td>
                                        <s:submit value="update"/>
                                        <s:hidden name="lastSearchvalue" value="%{searchValue}"/>
                                    </td>
                                </tr> 
                            </s:form>
                        </s:iterator>                
                    </tbody>

                </table>
            </s:if>
            <s:else>
                no data to display
            </s:else>
        </div>
        <s:a href="signOut">Sign Out</s:a> 

    </body>
</html>
