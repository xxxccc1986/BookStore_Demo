<%--
  Created by IntelliJ IDEA.
  User: hcxs1986
  Date: 2022/3/26
  Time: 18:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
<%--    <a href="pages/manager/book_manager.jsp">图书管理</a>--%>
<%--  ?action=的属性值和通过反射动态调用方法是一样的，即通过action的值确定调用的是Servlet程序中的哪个方法  --%>
    <a href="http://localhost:8080/Books/manager/bookServlet?action=getPage">图书管理</a>
<%--    <a href="pages/manager/order_manager.jsp">订单管理</a>--%>
    <a href="orderServlet?action=showAllOrder">订单管理</a>
    <a href="index.jsp">返回商城</a>
</div>
