<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的订单</title>

	<%-- 静态包含 base标签、css样式、JQuery文件	--%>
	<%@ include file="/pages/common/head.jsp"%>

	<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	</style>

	<script	type="text/javascript">
		$(function () {
			$("#receiver").click(function () {
				var sp = $(this).parent().parent().find("td:eq(1)").text();
				return confirm("请确定是否签收金额为：" + sp + "的商品" );
			})
		})
	</script>

</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">我的订单</span>

		<%--	静态包含 登录成功之后的菜单	--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">

		<table>
			<tr>
				<td>日期</td>
				<td>金额</td>
				<td>状态</td>
				<td>详情</td>
				<td>签收</td>
			</tr>
			<C:forEach items="${requestScope.orders}" var="order">
				<tr>
					<td>${order.creatTime}</td>
					<td>${order.price}</td>
					<c:choose>
						<C:when test="${order.status == 0}">
							<td>未发货</td>
						</C:when>
						<C:when test="${order.status == 1}">
							<td>已发货</td>
						</C:when>
						<C:otherwise>
							<td>已签收</td>
						</C:otherwise>
					</c:choose>
					<td><a href="orderServlet?action=showOrderDetail&orderId=${order.orderId}"  >查看详情</a></td>
					<c:if test="${order.status == 0 ||order.status == 1}">
					<td><a id="receiver" href="orderServlet?action=receiver&orderId=${order.orderId}" >签收商品</a></td>
					</c:if>
					<c:if test="${order.status == 2}">
					<td><a>交易完成</a></td>
					</c:if>
				</tr>
			</C:forEach>

		</table>
		
	
	</div>


	<%-- 静态包含 页脚信息	--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>