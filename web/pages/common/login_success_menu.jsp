<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hcxs1986
  Date: 2022/3/26
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#test").click(function () {
            //根据判断用户名id是否为空设置查看订单的权限
            <C:if test="${empty sessionScope.user.id}">
            alert("请先登录！");
            return false;
            </C:if>
        })
    })
</script>
<div>
        <span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>&nbsp;&nbsp;
        <a id="test" href="orderServlet?action=showMyOrder&userId=${sessionScope.user.id}">我的订单</a>&nbsp;&nbsp;
        <a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
        <a href="index.jsp">返回</a>
</div>