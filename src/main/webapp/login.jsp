<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>易买网 - 首页</title>
<link type="text/css" rel="stylesheet" href="/static/css/style.css" />
<script type="text/javascript"></script>
	<script src="/static/scripts/jquery-2.1.0.js"></script>
</head>
<body>

<div id="register" class="wrap">
	<div class="shadow">
		<em class="corner lb"></em>
		<em class="corner rt"></em>
		<div class="box">
			<h1>欢迎来到易买网</h1>
			<%--<form id="loginForm" method="post" >--%>
				<table>
					<tr>
						<td class="field">用户名：</td>
						<td><input class="text" type="text" id="userName" name="hu_user_name" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><span></span></td>
					</tr>
					<tr>
						<td class="field1">登录</td>
						<td><input class="text1" type="password" id="passWord" name="hu_password" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><a href="retrieve_password.jsp">忘记密码</a></td>
		
					</tr>
					<%--<tr>
						<td class="field">验证码：</td>
						<td><input class="text verycode" type="text" name="veryCode" onfocus="FocusItem(this)" onblur="CheckItem(this);" /><img id="veryCode" src="code.jsp" /><span></span></td>
					</tr>--%>
					<tr>
						<td></td>
						<td><label class="ui-green"><input type="submit" name="submit" value="立即登录" /></label></td>
					</tr>
				</table>
		</div>
	</div>
	<div class="clear"></div>
</div>
<script>

   $('.ui-green').click(function(){
	   param="hu_user_name="+$('.text').val()+"&&hu_password="+$('.text1').val();
	   $.ajax({
 		   type:"POST",
		   dataType:"JSON",//返回的数据类型
		   url:"<%=path%>/buyLoginController/login",
		   data:param,//提交时传过去的参数
		   async:true,
		   success:function(ResponseText){//提交成功后触发的函数
			   if(ResponseText.isOk){
				   window.location.href="<%=path%>/buyLoginController/tomain"
			   }else {
				   alert(ResponseText.info);
			   }
		   }
	   })
   })

</script>
<div id="footer">
	Copyright &copy; 2016  上海海文 All Rights Reserved
</div>
</body>
</html>
