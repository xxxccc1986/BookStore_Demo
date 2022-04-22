<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>

<%-- 静态包含 base标签、css样式、JQuery文件	--%>
<%@ include file="/pages/common/head.jsp"%>

<script type="text/javascript">

	$(function () {
		/*
		验证用户名：必须由字母，数字下划线组成，并且长度为 5 到 12 位
		验证密码：必须由字母，数字下划线组成，并且长度为 5 到 12 位
		验证确认密码：和密码相同
		邮箱验证：xxxxx@xxx.com
		验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
		 */

		//给验证码绑上单击刷新事件
		$("#code01").click(function () {
			//this是当前正在响应的dom对象，src是dom对象的一个属性，此src属性可读，可写
			this.src = "${basePath}kaptcha.jpg?tt="+ new Date();
			<%--this.src = "${basePath}/kaptcha.jpg";--%>
		})


		//给注册按钮绑定点击事件
		$("#sub_btn").click(function () {
			//验证用户名是否符合规则
			var nameCheck = /^\w{5,12}$/;
			var username = $("#username").val();

			if (!nameCheck.test(username)){
				$("span.errorMsg").text("用户名不合法");
				return false;
			}

			//验证密码是否符合规则
			var passCheck = /^\w{5,12}$/;
			var password = $("#password").val();

			if (!passCheck.test(password)){
				$("span.errorMsg").text("密码不符合规则");
				return false;
			}

			//验证确认密码：和密码相同
			var rePass = $("#repwd").val();
			if (rePass != password){
				$("span.errorMsg").text("密码不一致");
				return false;
			}


			//验证邮箱是否符合规则
			var email = $("#email").val();
			var checkEmail = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

			if (!checkEmail.test(email)){
				$("span.errorMsg").text("邮箱不合法");
				return false;
			}

			//验证验证码是否正确 此处只需要验证是否不为空即可
			var code = $("#code").val();

			//去掉验证码前后空格
			var code = $.trim(code);

			if (code == null || code == ""){
				$("span.errorMsg").text("验证码不能为空");
				return false;
			}

			$("span.errorMsg").text("");

		});

		$("#username").blur(function () {
			//1.获取用户名
			var username = $(this).val();
			//使用js向服务器发起异步请求
			$.getJSON("http://localhost:8080/Books/userServlet","action=ajaxExistsUsername&username=" + username,function (data) {
					if (data.result){
						$("span.errorMsg").text("用户名已存在");
					}else {
						$("span.errorMsg").text("用户名可用");
					}
			})
		})
	});


</script>
<style type="text/css">
	.login_form{
		height:420px;
		margin-top: 25px;
	}
	
</style>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册会员</h1>
								<span class="errorMsg">
									${ empty requestScope.msg?"":requestScope.msg}
								</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="regist">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名"
										   autocomplete="off" tabindex="1" name="username" id="username"
						<%--	让表单项的数据重写回显在表单项内只需要在request对象内存储数据  --%>
						<%--	并在此处利用JSTL的标签获取保存在request中相应的属性值	 --%>
											value="${ requestScope.username }"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码"
										   autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码"
										   autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址"
										   autocomplete="off" tabindex="1" name="email" id="email"
											value="${requestScope.email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" name="code" style="width: 150px;" id="code"/>
									<img id="code01" alt="" src="kaptcha.jpg" style="width: 110px;height: 40px;float: right; margin-right: 5px">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>

		<%-- 静态包含 页脚信息	--%>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>