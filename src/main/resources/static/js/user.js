//用于显示当前页面
$(function () {
    $("#userManage").addClass('layui-this');
});


//重置
function searchReset() {
    $("#username").val("");
    $("#name").val("");
}


//用户添加
function userAdd() {
    layui.use('layer', function () {
        var layer = layui.layer;
        layer.open({
            type: 1,//类型
            area: ['400px', '420px'],//定义宽和高
            title: '添加用户',//题目
            shadeClose: true,//点击遮罩层关闭
            content: $('#userAddView')//打开的内容
        });
    })
}



