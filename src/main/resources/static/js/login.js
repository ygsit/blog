$(function () {
    //表单校验方法
    layui.use(['form', 'jquery'], function(){
        var form = layui.form;
        var $=layui.jquery;
        form.verify({
            //校验两次密码是否一致
            confirmPass:function(value){
                var passValue = $('#password').val();
                if( passValue!== value)
                    return '两次密码输入不一致！';
            }
            //我们既支持上述函数式的方式，也支持下述数组的形式
            //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            //真实姓名
            ,truename: [
                /^[\u4e00-\u9fa5]{2,4}$/, '您的输入有误，请输入2-4位中文'
            ]
            //手机号码
            ,phone: [
                /^(13|14|15|17|18|19)\d{9}$/, '手机号码不正确'
            ],
            //判断用户名是否不存在
            usernameIsNotExist:function(value) {
                var flag = false;
                $.ajax({
                    url: "/user/usernameIsExist?username="+value,
                    async: false, //改为同步请求
                    dataType: 'json',
                    type: "POST",
                    success: function (result) {
                        flag = result;
                    },
                });
                if (!flag) {
                    return '用户名不存在';
                }
            },
            //判断用户名是否已经存在
            usernameIsExist:function(value) {
                var flag = false;
                $.ajax({
                    url: "/user/usernameIsExist?username="+value,
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
                    return '用户名已存在';
                }
            }
        });

    });
})


//忘记密码
function forgetPassword() {
    layui.use('layer',function () {
        var layer = layui.layer;
        layer.open({
            type:1,//类型
            area:['400px','300px'],//定义宽和高
            title:'忘记密码',//题目
            shadeClose:true,//点击遮罩层关闭
            content: $('#forgetPasswordView')//打开的内容
        });
    })
}


//用户注册
function userRegister() {
    layui.use('layer',function () {
        var layer = layui.layer;
        layer.open({
            type:1,//类型
            area:['400px','420px'],//定义宽和高
            title:'用户注册',//题目
            shadeClose:true,//点击遮罩层关闭
            content: $('#userRegisterView')//打开的内容
        });
    })
}


//校验用户名
function checkUsername() {
    var usernameValue = $("#loginUsername").val();
    if(usernameValue.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('用户名不能为空', '#loginUsername', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        return false;
    } else {
        return true;
    }
}

//校验密码
function checkPassword() {
    var passwordValue = $("#loginPassword").val();
    if(passwordValue.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('密码不能为空', '#loginPassword', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        return false;
    } else {
        return true;
    }
}

//校验验证码
function checkVercode() {
    var codeValue = $("#vercode").val();
    if(codeValue.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('验证码不能为空', '#vercode', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        return false;
    } else {
        return true;
    }
}

//登录提交验证
function checkForm() {
    if(checkUsername() && checkPassword() && checkVercode()){
        return true;
    }
    else {
        return false;
    }
}


//点击验证码变化
function changeCode() {
    //1.获取验证码图片对象
    var vcode = document.getElementById("vcode");
    //2.设置其src属性，加时间戳
    vcode.src = '/getVerifyCode?time=' + new Date().getTime()
}

