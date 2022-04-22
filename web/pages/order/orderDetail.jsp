<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单详情</title>

	<%-- 静态包含 base标签、css样式、JQuery文件	--%>
	<%@ include file="/pages/common/head.jsp"%>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">订单详情</span>
		<div>
			<span>欢迎<span class="um_span">${sessionScope.user.username}</span>光临书城</span>&nbsp;&nbsp;

			<c:if test="${sessionScope.user.id == 1}">
				<%--	用于管理员界面返回订单查询处	--%>
				<a href="orderServlet?action=showAllOrder">返回</a>
			</c:if>

			<c:if test="${sessionScope.user.id != 1}">
				<%--	用于普通用户界面返回订单查询处	--%>
				<a href="orderServlet?action=showMyOrder&userId=${sessionScope.user.id}">返回</a>
			</c:if>
<%--			<a href="orderServlet?action=showAllOrder">返回</a>--%>
		</div>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>订单号</td>
			<tr>
			<tr>
				<td>${requestScope.orderItem.name}</td>
				<td>${requestScope.orderItem.count}</td>
				<td>${requestScope.orderItem.price}</td>
				<td>${requestScope.orderItem.totalPrice}</td>
				<td>${requestScope.orderItem.orderId}</td>
			<tr>
		</table>


	</div>


	<%-- 静态包含 页脚信息	--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>