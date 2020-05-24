//用于显示当前页面
$(function () {
    $("#index").addClass('layui-this');
});


function newPage(bid) {
    console.log(bid);
    location.href = "/comment/toCommentPage/"+bid+"/1";
}