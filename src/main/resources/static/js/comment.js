//用于显示当前页面
$(function () {
    //获取所有的博客
    $.post("/blog/getAllBlogs", function (result) {
        let jsonArr = JSON.parse(result);
        for(let obj of jsonArr){
            $('#chooseBlog').append('<option value="'+obj.bid+'">'+obj.title+'</option>')
        }
    })
});


//点击回复
function reply(parentId, rootId, username) {
    console.log(parentId);
    console.log(username);
    $("[name='content']").attr("placeholder", "回复：" + username);
    $("[name='parentId']").val(parentId);
    $("[name='rootId']").val(rootId);
    $(".cance-reply").css("opacity", "1");
    $(".reply-btn").css("cursor", "pointer");

}

//取消回复
function canceReply() {
    $("[name='content']").attr("placeholder", "请输入评论内容");
    $("[name='parentId']").val("");
    $("[name='rootId']").val("");
    $(".cance-reply").css("opacity", "0");
    $(".reply-btn").css("cursor", "default");
}

//评论内容校验
function contentCheck() {
    var value = $("#commentContent").val();
    if(value == null || value.trim() == ""){
        layui.use('layer',function () {
            var layer = layui.layer;
            layer.tips('评论内容不能为空', '#commentContent', {
                tips: [1, '#3595CC'],
                time: 4000
            });
        });
        // $("#commentContent").css("backgroundColor", "#FFF6F6");
        return false;
    } else {
        return true;
    }
}

//表单提交校验
function submitComment() {
    if(contentCheck()){
        return true;
    }
    else {
        return false;
    }
}

//重置
function searchReset() {
    $("#chooseBlog option:first").prop("selected", 'selected');
}
