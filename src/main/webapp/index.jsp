<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<script type="text/javascript" src="http://apps.bdimg.com/libs/jquery/2.1.1/jquery.min.js"></script>
<%
    String path = request.getContextPath();
%>
<script language="JavaScript">
    $(function () {
        $("#search").click(function () {
            $.ajax({
                type: "post",
                url: "<%=path%>/student/findById",
                data: $("#sid"),
                dataType: "json",
                success: function (data) {
                    var html = "";
                    html +=
                        "<td>" + data.sid + "</td><td>" + data.classes.cname + "</td><td>" + data.sname
                        + "</td><td>" + data.sex + "</td><td>" + data.birthplace + "</td>";
                    $("#student").html(html);
                },
                //null值不会被success回调函数捕获，因此可以通过error来实现该功能
                error: function () {
                    alert("请输入正确的学号！");
                }
            })
        })
    })
</script>
<body>
<center>
    <div style="margin-top: 25px">
        请输入所要查询的学号:<input type="text" id="sid" name="sid"/><input id="search" style="margin-left: 10px" type="button" value="搜索"><br/>
    </div>
    <div style="margin-top: 50px">
        <table border="1">
            <tr>
                <td>学号</td>
                <td>班级</td>
                <td>姓名</td>
                <td>性别</td>
                <td>出生地</td>
            </tr>
            <tr id="student">

            </tr>
        </table>
    </div>
</center>
</body>
</html>
