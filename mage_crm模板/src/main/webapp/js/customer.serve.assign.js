function openAssignDlg(){
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length!=1){
        $.messager.alert("crm","请选择一条记录进行分配","info");
        return;
    }
    $("#fm").form('load',rows[0]);
    $("#dlg").dialog("open");
}

function closeCustomerServeDialog() {
    $("#dlg").dialog("close");
}
function addCustomerServeAssign() {
    $("#fm").form("submit",{
        url:ctx+"/customer_serve/update",
        onSubmit:function (params){
            params.state=2;//2分配了未处理
            return $("#fm").form("validate");
        },
        success:function (data) {
            data=JSON.parse(data);
            $.messager.alert("crm",data.msg,"info")
            if(data.code==200){
                closeCustomerServeDialog();
                $("#dg").datagrid("load");
            }
        }
    })
}