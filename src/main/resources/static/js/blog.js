//博客标题不能为空
function checkTitle() {
    var titleValue = $("#title").val();
    if(titleValue.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('博客标题不能为空', '#title', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        return false;
    } else {
        return true;
    }
}

//博客内容不能为空
function checkContent() {
    var contentValue = $("#content").val();
    if(contentValue.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('博客内容不能为空', '#content', {
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
    if(checkTitle() && checkContent()){
        return true;
    }
    else {
        return false;
    }
}

//重置
function searchReset() {
    $("#title").val("");
}
