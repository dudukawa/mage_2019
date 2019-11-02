function openTab(text, url, iconCls){
    if($("#tabs").tabs("exists",text)){
        $("#tabs").tabs("select",text);
    }else{
        var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='" + url + "'></iframe>";
        $("#tabs").tabs("add",{
            title:text,
            iconCls:iconCls,
            closable:true,
            content:content
        });
    }
}

//修改密码
function openPasswordModifyDialog() {
    $("#dlg").dialog("open");
}
//关闭修改密码对话框
function closePasswordModifyDialog() {
    $("#dlg").dialog("close");
}
//保存按钮
function modifyPassword() {
    $("#fm").form("submit",{
        url:ctx+"/user/updatePwd",
        //表单提交之前触发，返回false可以阻止提交
        onsubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            var jsonData = JSON.parse(data);
            if(jsonData.code == 200){
                $.messager.alert('来自crm系统','修改密码成功，两秒后跳转登录...','info');
                setTimeout(function(){
                    $.removeCookie("userName");
                    $.removeCookie("trueName");
                    $.removeCookie("id");
                    window.location.href="index";
                },2000);
            }else{
                $.messager.alert('来自crm系统',jsonData.msg,'info');
            }
        }
    })
}
//安全退出
function logout() {
    $.messager.confirm('来自crm','你确定要退出吗？',function (r) {
        if(r){
            setTimeout(function (){
                $.removeCookie("id");
                $.removeCookie("userName");
                $.removeCookie("trueName");
                window.location.href="index";
            },2000);
        }
    });
}

