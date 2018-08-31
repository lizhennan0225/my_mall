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
    <header class="layui-elip">登录</header>
    <form class="layui-form" action="">
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
                <button class="layui-btn" lay-submit="" lay-filter="demo1">登录</button>
                <%--<button type="reset" class="layui-btn layui-btn-primary">重置</button>--%>
            </div>
        </div>
        <hr/>
        <div class="layui-form-item">
            <p><a href="/WEB-INF/jsps/register.jsp" class="fl">立即注册</a><a href="forget.jsp" class="fr">忘记密码</a></p>
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
            // console.log(data.field);
            $.ajax({
                url: 'login.php',
                data: data.field,
                dataType: 'text',
                type: 'post',
                success: function (data) {
                    if (data == '1') {
                        location.href = "../index.php";
                    } else {
                        layer.msg('登录名或密码错误');
                    }
                }
            })
            return false;
        })

    });
</script>

</body>
</html>