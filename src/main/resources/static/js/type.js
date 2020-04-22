$(function () {

    //表单校验方法
    layui.use(['form', 'jquery'], function(){
        var form = layui.form;
        var $=layui.jquery;
        form.verify({
            //判断分类名是否已经存在
            typeNameIsNotExist:function(value) {
                var flag = false;
                $.ajax({
                    url: "/type/typeNameIsExist?name="+value,
                    async: false, //改为同步请求
                    dataType: 'json',
                    type: "POST",
                    success: function (result) {
                        flag = result;
                    },
                    error: function () {
                        console.log("调用Ajax失败")
                    }
                });
                if (flag) {
                    return '分类名已存在';
                }
            }
        });

    });
})



//跳转
function findByTid(el) {
    if(el.target.className != "sort_label"){
        var tid = $(el.target).parent('.sort_label').children('.type_id')[0].value;
        location.href = "/type/toType?tid="+tid;
    }
}


//增加分类
function addType() {
    location.href="/type/toTypeAddPage"
}


//显示添加窗口
function showAddView() {
    layui.use('layer',function () {
        var layer = layui.layer;
        layer.open({
            type:1,//类型
            area:['400px','220px'],//定义宽和高
            title:'添加分类',//题目
            shadeClose:true,//点击遮罩层关闭
            content: $('#addTypeView')//打开的内容
        });
    })
}

//重置
function searchReset() {
    $("#name").val("");
}

//校验分类名
function checkTypeName() {
    var typeName = $("#typeName").val();
    if(typeName.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('分类名不能为空', '#typeName', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        return false;
    }
    //分类名是否存在
    $.post("/type/typeNameIsExist", {"name":typeName}, function (result) {
        if(result == "true"){
            layui.use('layer',function () {
                var layer = layui.layer;
                layer.tips('分类名已存在', '#typeName', {
                    tips: [1, '#3595CC'],
                    time: 4000
                });
            });
            return false;
        }
    })
    return true;
}

//登录提交验证
function checkForm() {
    if(checkTypeName()){
        return true;
    }
    else {
        return false;
    }
}