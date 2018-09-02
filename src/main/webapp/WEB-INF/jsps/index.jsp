<%--
  Created by IntelliJ IDEA.
  User: lzn
  Date: 2018/8/30
  Time: 10:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
<head>
    <meta charset="UTF-8">

    <title>登录页</title>
    <link rel="stylesheet" href="<%=basePath%>resource/plug-in/layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/style.css">
</head>
<body>

<div class="login-main">
    <header class="layui-elip">登录Tomcat1</header>
    <form class="layui-form">
        <div class="layui-form-item">
            <%--<label class="layui-form-label">账号</label>--%>
            <div class="layui-input-inline">
                <input type="text" name="username" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <%--<label class="layui-form-label">密码</label>--%>
            <div class="layui-input-inline">
                <input type="text" name="password" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-inline">
                <button class="layui-btn" lay-submit lay-filter="login">登录</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
        <hr/>
        <div class="layui-form-item">
            <p><a href="<%=basePath%>user/register_index" class="fl">立即注册</a><a href="forget.jsp" class="fr">忘记密码</a>
            </p>
            <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
        </div>

    </form>
</div>


<script src="<%=basePath%>resource/plug-in/layui/layui.all.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {

        // 操作对象
        var form = layui.form;
        var $ = layui.jquery;
        form.on('submit(login)', function (data) {
            $.ajax({
                url: '<%=basePath%>user/login',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: 'json',
                type: 'POST',
                success: function (data) {
                    if (data.status == 0) {
                        <%--$.get("<%=basePath%>user/manage/admin");--%>
                        location.href = "<%=basePath%>user/manage/admin";
                    } else {
                        layer.msg('登录名或密码错误');
                    }
                }
            })
            return false; //为了不使layui form 表单在自动提交一次无效请求
        })

    });
</script>

</body>
</html>