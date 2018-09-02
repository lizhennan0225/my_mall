/**
 * Created by lzn on 2018/8/30.
 */
var $ = layui.jquery;
var Admin = {
    URL: {
        logout: function (basePath) {
            return basePath + "user/manage/logout";
        }
    },
    logout: function (basePath) {
        var url = Admin.URL.logout(basePath);
        $.ajax({
            url: url,
            data:"",
            contentType: "application/json",
            dataType: 'json',
            type: 'POST',
            success: function (data) {
                if (data.status == 0) {
                    location.href = basePath;
                } else {
                    layer.msg(data.msg);
                }
            }
        })
    }
}
