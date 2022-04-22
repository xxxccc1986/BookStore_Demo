<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>

	<%-- 静态包含 base标签、css样式、JQuery文件	--%>
	<%@ include file="/pages/common/head.jsp"%>


	<script type="text/javascript">
		<%-- 网页加载完成后执行	--%>
		$(function () {
			//给删除标签绑上单击事件
			$("a.de01").click(function () {
				//获取商品的名称
				var text = $(this).parent().parent().find("td:first").text();
				return confirm("确定要删除商品名为：" + text)
			});

			// 给清空购物车标签绑上单击事件
			$("#clear").click(function () {
				return confirm("确定要清空购物车吗？")
			});

			//给提供输入框修改数量标签绑定改变事件
			$(".updateCount").change(function () {
				//获取商品的名称
				var text = $(this).parent().parent().find("td:first").text();
				//获取商品的修改值
				var count = $(this).val();
				//获取商品的id
				var bookId = $(this).attr("bookId");
				if (confirm("你确定要将商品 "  + text + "的数量修改为" + count + "吗？")){
					location.href = "cartServlet?action=UpdateCount&bookId=" + bookId + "&count=" + count;
				}else {
					//defaultValue属性是表单项dom对象的属性，它表示默认value属性值
					this.value = this.defaultValue;
				}

			})

		})

	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>

		<%--	静态包含 登录成功之后的菜单	--%>
		<%@ include file="/pages/common/login_success_menu.jsp"%>

	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
			<%--	当前购物车为空			--%>
			<c:if test="${empty sessionScope.cart.items}">
				<td colspan="5"><a href="index.jsp">购物车啥也没有，先去浏览添加吧！！</a> </td>
			</c:if>

			<%--	如果购物车为非空的情况下给予输出以下标签	--%>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
<%--						<td>${entry.value.count}</td>--%>
						<td>
						<%--	将商品数量由确定的数字改为提供输入框修改		--%>
							<input class="updateCount"
								   bookId="${entry.value.id}"
								   style="width: 80px;height: 20px" type="text" value="${entry.value.count}" >
						</td>
						<td>${entry.value.price}</td>
						<td>${entry.value.totalPrice}</td>
						<td><a class="de01" href="cartServlet?action=deleteItem&id=${entry.value.id}" >删除</a></td>
					</tr>
				</c:forEach>
			</c:if>

		</table>

		<c:if test="${not empty sessionScope.cart.items}">

			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.total}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
				<span class="cart_span"><a  id="clear" href="cartServlet?action=clearItem">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>

	</div>


	<%-- 静态包含 页脚信息	--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>