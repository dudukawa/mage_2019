function searchCustomer() {
    $("#dg").datagrid('load',{
        khno:$("#s_khno").val(),
        name:$("#s_name").val()
    })
}

function openCustomerAddDialog() {
    $("#fm").form("clear");
    $("#dlg").dialog("open").dialog("添加客户信息");
}

function openCustomerModifyDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length!=1){
        $.messager.alert("来自Crm","只能选择一条记录进行修改",'info');
        return;
    }
    $("#fm").form("clear");
    //填充表单信息
    $("#fm").form('load',rows[0]);
    $("#dlg").dialog("open").dialog("修改客户信息");
}
function saveOrUpdateCustomer() {
    var id = $("#id").val();
    var url = ctx+"/customer/insert";
    if(id!=null){
        url = ctx+"/customer/update";
    }
    $("#fm").form('submit',{
        url:url,
        onSubmit:function () {
            return $("#fm").form("validate");
        },
        success:function (data) {
            data = JSON.parse(data);
            if(data.code==200){
                $.messager.alert("来自Crm",data.msg,'info');
                closeCustomerDialog();
                searchCustomer();
            }else {
                $.messager.alert("来自Crm",data.msg,'error');
            }
        }
    })

}

//关闭对话框
function closeCustomerDialog() {
    $("#dlg").dialog("close");
}

function deleteCustomer() {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length<1){
        $.messager.alert("来自Crm","请选择一条要删除的数据(可多选)","warning")
        return;
    }
    var ids="id=";
    for(var i=0;i<rows.length;i++){
        if(i<rows.length-1){
            ids=ids+rows[i].id+"&id=";
        }else {
            ids=ids+rows[i].id;
        }
    }
    $.messager.confirm("来自Crm","确认要删除这些记录吗？",function (r) {
        if(r){
            $.ajax({
                url:ctx+"/customer/delete",
                data:ids,
                dataType:'json',
                success:function (data) {
                    if(data.code==200){
                        $.messager.alert("来自Crm","删除成功","info");
                        searchCustomer();
                    }else{
                        $.messager.alert("来自Crm","删除失败","error");
                    }
                }
            });
        }
    })
}

function openCustomerOtherInfo(title,type) {
    var rows = $("#dg").datagrid("getSelections");
    if(rows.length!=1){
        $.messager.alert("来自Crm","请选择一条要删除的数据","warning")
        return;
    }
    //打开选项卡
    window.parent.openTab(title,ctx+"/customer/openCustomerOtherInfo/"+rows[0].id);
}
