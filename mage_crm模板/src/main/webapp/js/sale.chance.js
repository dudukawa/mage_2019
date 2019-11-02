function formatterState(val) {
    if (val==0){
        return "未分配";
    }else if(val==1){
        return "已分配";
    }else {
        return "未定义";
    }
}
function searchSaleChances() {
    $("#dg").datagrid("load",{
        createMan:$("#createMan").val(),
        customerName:$("#customerName").val(),
        createDate:$("#createDate").datebox("getValue"),
        state:$("#state").combobox("getValue")
    })
}

//添加工具栏
function openAddAccountDialog() {
    $("#fm").form("clear");
    $("#dlg").dialog("setTitle","添加营销机会记录")
    $("#dlg").dialog("open");
}
function closeDialog() {
    $("#dlg").dialog("close");
}
function openModifyAccountDialog() {
    var rows = $("#dg").datagrid("getSelections");
    if(!(rows.length==1)){
        $.messager.alert("来自Crm","选中一条记录","warning");
        return;
    }
    $("#fm").form("clear");
    $("#fm").form('load',rows[0]);
    $("#dlg").dialog("open").dialog("setTitle","修改营销机会记录");
}

//添加或修改操作
function saveAccount() {
    var id = $("#id").val();
    var url = ctx+"/sale_chance/update";
    if(isEmpty(id)){
        url = ctx+"/sale_chance/insert";
    }
    $("#fm").form("submit",{
        url:url,
        onSubmit:function (params) {
            //添加一个属性createMan，默认添加一个创建人
            params.createMan = $.cookie("trueName");
            return $("#fm").form("validate");
        },
        success:function (data) {
            var parse = JSON.parse(data);
            if(parse.code==200){
                $.messager.alert("来自Crm系统",parse.msg,"info");
                closeDialog();
                searchSaleChances();
            }else{
                $.messager.alert("来自Crm系统",parse.msg,"info");
            }
        }
    })
}

//删除
function deleteAccount() {
    var rows=$("#dg").datagrid("getSelections");
    if (rows.length==0){
        $.messager.alert("来自crm","至少选中一条所要删除的记录","error");
        return;
    }
    var params="id=";
    for (var i=0;i<rows.length;i++){
        if(i<rows.length-1){
            params=params+rows[i].id+"&id=";
        }else {
            params=params+rows[i].id;
        }
    }
    console.log(params);
    $.messager.confirm("来自Crm","你确定要删除所选的记录嘛？",function (r) {
        if (r){
            $.ajax({
                url:ctx+"/sale_chance/delete",
                data:params,
                dataType:'json',
                success:function (data) {
                    if (data.code==200){
                        $.messager.alert("来自crm",data.msg,"info");
                        searchSaleChances();
                    }else {
                        $.messager.alert("来自crm","删除失败","info");
                    }
                }
            })
        }
    })
}
