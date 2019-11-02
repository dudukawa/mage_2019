function saveCustomerService() {
    $("#fm").form('submit',{
        url:ctx+"/customer_serve/insert",
        onSubmit:function (params) {
            //设置创建人，当前登录用户，从cookie中获取
            params.createPeople = $.cookie("trueName");
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            $.messager.alert("crm",data.msg,"info")
            if(data.code == 200){
                $("#fm").form("clear");
            }
        }
    })
}

function resetValue() {
    $("#fm").form("reset");
}