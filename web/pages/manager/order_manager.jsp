<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>订单管理</title>

	<%-- 静态包含 base标签、css样式、JQuery文件	--%>
	<%@ include file="/pages/common/head.jsp"%>

	<script type="text/javascript">
		$(function () {
			$("a.send").click(function () {
				var text = $(this).parent().parent().find("td:eq(1)").text();
				return confirm("确定真的发出金额为" + text + "的商品吗？");
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">订单管理系统</span>

			<%--	静态包含 manager管理模块的菜单		--%>
			<%@ include file="/pages/common/manager_menu.jsp"%>

	</div>
	
	<div id="main">
		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
			</tr>
			<c:forEach items="${requestScope.allOrder}" var="order">
				<tr>
					<td>${order.creatTime}</td>
					<td>${order.price}</td>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}">查看详情</a></td>
					<c:if test="${order.status == 0}">
					<td><a class="send" href="orderServlet?action=sendOrder&orderId=${order.orderId}">点击发货</a></td>
					</c:if>
					<c:if test="${order.status != 0}">
					<td><a style="color:#000;">已发货</a></td>
					</c:if>

				</tr>
			</c:forEach>

		</table>

	</div>


	<%-- 静态包含 页脚信息	--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>