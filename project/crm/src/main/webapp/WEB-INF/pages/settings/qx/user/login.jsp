<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
	/*getScheme获取协议，如http；getServerName获取服务器的ip；getServerPort获取端口号；getContextPath	获取应用名字*/
	String basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
<%--	动态获取--%>
	<base href="<%=basePath%>">
<meta charset="UTF-8">
<link href="jquery/bootstrap_3.3.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script type="text/javascript" src="jquery/jquery-1.11.1-min.js"></script>
<script type="text/javascript" src="jquery/bootstrap_3.3.0/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$(function () {
			//给整个浏览器窗口添加键盘按下事件
			$(window).keydown(function (event) {
				//按回车键发送请求
				if(event.keyCode==13){
					//直接触发单击事件
					$("#loginBtn").click();
				}
			});


			//给登录按钮添加单击事件
			$("#loginBtn").click(function (){
				//收集参数,去空格
				let loginAct=$.trim($("#loginAct").val());
				let loginPwd=$.trim($("#loginPwd").val());
				// $("#isRemPwd").attr("")不能用来获取值为true或者false的属性的值
				let isRemPwd = $("#isRemPwd").prop("checked");
				//表单验证
				if (loginAct==""){
					alert("用户名不能为空");
					return;
				}
				if (loginPwd==""){
					alert("密码不能为空");
					return;
				}
				//显示正在验证
				$("#msg").text("正在努力验证...");
				//发送请求
				$.ajax({
					url:'settings/qx/user/login.do',
					data:{
						loginAct:loginAct,
						loginPwd:loginPwd,
						isRemPwd:isRemPwd
					},
					type:'post',
					dataType:'json',
					success:function (data) {
						if (data.code=="1"){
							//跳转到业务主页面
							window.location.href="workbench/index.do";
						}else {
							$("#msg").text(data.message);
						}
					},
					//ajax发送请求之前会执行该函数,如果该函数返回值为true,则ajax向后台发请求，false则不发
					// beforeSend:function () {}
				});

			});

		});
	</script>
</head>
<body>
	<div style="position: absolute; top: 0px; left: 0px; width: 60%;">
		<img src="image/IMG_7114.JPG" style="width: 100%; height: 90%; position: relative; top: 50px;">
	</div>
	<div id="top" style="height: 50px; background-color: #3C3C3C; width: 100%;">
		<div style="position: absolute; top: 5px; left: 0px; font-size: 30px; font-weight: 400; color: white; font-family: 'times new roman'">CRM &nbsp;<span style="font-size: 12px;">&copy;2022&nbsp;jerry</span></div>
	</div>
	
	<div style="position: absolute; top: 120px; right: 100px;width:450px;height:400px;border:1px solid #D5D5D5">
		<div style="position: absolute; top: 0px; right: 60px;">
			<div class="page-header">
				<h1>登录</h1>
			</div>
			<for class="form-horizontal" role="form">
				<div class="form-group form-group-lg">
					<div style="width: 350px;">
						<input class="form-control" id="loginAct" type="text" value="${cookie.loginAct.value}" placeholder="用户名">
					</div>
					<div style="width: 350px; position: relative;top: 20px;">
						<input class="form-control" id="loginPwd" type="password" value="${cookie.loginPwd.value}" placeholder="密码">
					</div>
					<div class="checkbox"  style="position: relative;top: 30px; left: 10px;">
						<label>
							<c:if test="${not empty cookie.loginAct and not empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd" checked> 十天内免登录
							</c:if>
							<c:if test="${empty cookie.loginAct or empty cookie.loginPwd}">
								<input type="checkbox" id="isRemPwd"> 十天内免登录
							</c:if>
						</label>
						&nbsp;&nbsp;
						<span id="msg" style="color: red"></span>
					</div>
					<button type="submit" id="loginBtn" class="btn btn-primary btn-lg btn-block"  style="width: 350px; position: relative;top: 45px;">登录</button>
				</div>
			</for>
		</div>
	</div>
</body>
</html>