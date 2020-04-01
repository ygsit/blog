//用于显示当前页面
$(function () {
    $("#type").addClass('layui-this');
});



function findByTid(el) {
    console.log(el)
    location.href = "/type/toType?tid="+el;

    /*if(el.target.className != "sort_label"){
        var tid = $(el.target).parent('.sort_label').children('.type_id')[0].value;
        location.href = "/type/toType?tid="+tid;
    }*/
}