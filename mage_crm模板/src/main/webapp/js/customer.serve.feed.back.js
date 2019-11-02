function openFeedBackDlg() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length!=1){
        $.messager.alert("来自Crm","选择一条反馈的数据！","info");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open");
}

function addCustomerServeServiceFeedBack() {
    $("#fm").form("submit",{
        url:ctx+"/customer_serve/update",
        onSubmit:function (params) {
            params.state=4;
            return $("#fm").form("validate");
        },
        success:function (data) {
            var data = JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自Crm",data.msg,"info");
                closeCustomerServeDialog();
            }else{
                $.messager.alert("来自Crm",data.msg,"error");
            }
        }
    });
}

function closeCustomerServeDialog() {
    $("#dlg").dialog("close");
}