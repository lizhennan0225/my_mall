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

    <title>注册页</title>
    <link rel="stylesheet" href="<%=basePath%>resource/plug-in/layui/css/layui.css">
    <link rel="stylesheet" href="<%=basePath%>resource/css/register.css">
</head>
<body>

<div class="register-main">
    <header class="layui-elip">注册</header>
    <form class="layui-form" action="">
        <div class="layui-form-item">
            <label class="layui-form-label">用户名</label>
            <div class="layui-input-block">
                <div class="layui-inline" style="width: 85%">
                    <input type="text" id="user" name="username" required lay-verify="required" placeholder="请输入用户名"
                           autocomplete="off" class="layui-input">
                </div>
                <!-- 对号 -->
                <div class="layui-inline" style="width: 5%">
                    <i class="layui-icon" id="ri" style="color: green;font-weight: bolder;" hidden></i>
                </div>
                <!-- 错号 -->
                <div class="layui-inline" style="width: 5%">
                    <i class="layui-icon" id="wr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-block">
                <div class="layui-inline" style="width: 85%">
                    <input type="text" id="pwd" name="password" lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
                <!-- 对号 -->
                <div class="layui-inline">
                    <i class="layui-icon" id="pri" style="color: green;font-weight: bolder;" hidden></i>
                </div>
                <!-- 错号 -->
                <div class="layui-inline">
                    <i class="layui-icon" id="pwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码确认</label>
            <div class="layui-input-block">
                <div class="layui-inline" style="width: 85%">
                    <input type="text" id="rpwd" name="password" lay-verify="required" autocomplete="off"
                           class="layui-input">
                </div>
                <!-- 对号 -->
                <div class="layui-inline">
                    <i class="layui-icon" id="rpri" style="color: green;font-weight: bolder;" hidden></i>
                </div>
                <!-- 错号 -->
                <div class="layui-inline">
                    <i class="layui-icon" id="rpwr" style="color: red; font-weight: bolder;" hidden>ဆ</i>
                </div>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block" style="width: 85%">
                <input type="email" name="email" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">电话</label>
            <div class="layui-input-block" style="width: 85%">
                <input type="text" name="phone" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码找回问题</label>
            <div class="layui-input-block" style="width: 85%">
                <input type="text" name="question" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">密码找回答案</label>
            <div class="layui-input-block" style="width: 85%">
                <input type="text" name="answer" lay-verify="required" autocomplete="off"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-block" style="width: 85%">
                <input type="radio" name="role" value="1" title="管理员" checked="">
                <input type="radio" name="role" value="0" title="普通用户">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-input-block" style="width: 85%">
                <button class="layui-btn" lay-submit="" lay-filter="register">注册</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
            </div>
        </div>
    </form>
</div>


<script src="<%=basePath%>resource/plug-in/layui/layui.all.js"></script>
<script type="text/javascript">
    layui.use(['form', 'layer', 'jquery'], function () {
        var form = layui.form;
        var $ = layui.jquery;
        var layer = layui.layer;

        //监听表单user失去焦点的事件
        $('#user').blur(function () {
            var username = $(this).val();
            //alert(user);
            $.ajax({
                url: '<%=basePath%>user/check_vailed/' + username,
                type: 'get',
                dataType: 'json',
                contentType: "application/x-www-form-urlencoded",
                //验证用户名是否可用
                success: function (result) {
                    console.log(result.status);
                    if (result.status == 0) {
                        $('#ri').removeAttr('hidden');
                        $('#wr').attr('hidden', 'hidden');
                    } else {
                        $('#wr').removeAttr('hidden');
                        $('#ri').attr('hidden', 'hidden');
                        layer.msg('当前用户名已被占用! ')
                    }
                }
            })
        });


        // you code ...
        // 为密码添加正则验证
        $('#pwd').blur(function () {
            var reg = /^[\w]{6,12}$/;
            if (!($('#pwd').val().match(reg))) {
                //layer.msg('请输入合法密码');
                $('#pwr').removeAttr('hidden');
                $('#pri').attr('hidden', 'hidden');
                layer.msg('请输入合法密码');
            } else {
                $('#pri').removeAttr('hidden');
                $('#pwr').attr('hidden', 'hidden');
            }
        });

        //验证两次密码是否一致
        $('#rpwd').blur(function () {
            if ($('#pwd').val() != $('#rpwd').val()) {
                $('#rpwr').removeAttr('hidden');
                $('#rpri').attr('hidden', 'hidden');
                layer.msg('两次输入密码不一致!');
            } else {
                $('#rpri').removeAttr('hidden');
                $('#rpwr').attr('hidden', 'hidden');
            }
            ;
        });


        form.on('submit(register)', function (data) {
            console.log(data.field);
            $.ajax({
                url: '<%=basePath%>user/register',
                data: JSON.stringify(data.field),
                contentType: "application/json",
                dataType: "json",
                type: 'post',
                success: function (data) {
                    if (data.status == 0) {
                        location.href = '<%=basePath%>';
                    } else {
                        layer.msg('注册失败');
                    }
                }
            })
            return false;
        })

    });
</script>

</body>
</html>