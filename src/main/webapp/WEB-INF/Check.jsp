<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/8/16 0016
  Time: 下午 5:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>验证码校验</title>
</head>
<body>
  <%

      String checkCode = request.getParameter("checkCode");
      if(checkCode.equals("")||checkCode==null){
          out.print("<script>alert('请输入验证码');window.location.href('index.jsp')</script>");
      }else{
          if(!checkCode.equalsIgnoreCase((String)session.getAttribute("randCheckCode"))){
              out.print("<script>alert('验证码不正确,请重新输入');history.back(-1);</script>");
          }else{
              out.print("登录成功");
          }
      }


  %>

</body>
</html>