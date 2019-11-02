function openProceedDlg() {
    var rows = $("#dg").datagrid('getSelections');
    if(rows.length!=1){
        $.messager.alert("来自Crm","请选一条要处理的数据！","warning");
        return;
    }
    $("#fm").form("load",rows[0]);
    $("#dlg").dialog("open");
}

function addCustomerServeServiceProceed(){
    $("#fm").form("submit",{
        url:ctx+"/customer_serve/update",
        onSubmit:function(params){
            params.state=3;
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自Crm",data.msg,"info");
                closeCustomerServeDialog();
                $("#dg").datagrid("load");
            }else {
                $.messager.alert("来自Crm",data.msg,"error");
            }
        }
    })
}

function closeCustomerServeDialog() {
    $("#dlg").dialog("close");
}