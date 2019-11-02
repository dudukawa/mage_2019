$(function(){
    var devResult = $("#devResult").val();
    var saleChanceId = $("#saleChanceId").val();

    if(devResult==2||devResult==3){
        $("#toolbar").remove();
    }
    $("#dg").edatagrid({
        url:ctx+"/cus_dev_plan/queryCusDevPlans?saleChanceId="+saleChanceId,
        saveUrl:ctx+"/cus_dev_plan/insertCusDevPlans?saleChanceId="+saleChanceId,
        updateUrl:ctx+"/cus_dev_plan/updateCusDevPlans?saleChanceId="+saleChanceId,
    })
})

function saveCusDevPlan() {
    $("#dg").edatagrid("saveRow");
    $("#dg").edatagrid("load");
}
function updateCusDevPlan() {
    $("#dg").edatagrid("saveRow");
}
function updateSaleChanceDevResult(devResult) {
    $.messager.confirm("来自Crm系统","确定执行该操作？",function (r) {
        if(r){
            $.ajax({
                type:'post',
                url:ctx+"/sale_chance/updateSaleChanceDevResultById",
                data:"devResult="+devResult+"&saleChanceId="+$("#saleChanceId").val(),
                dataType:'json',
                success:function (data) {
                    console.log(data.code);
                    if(data.code==200){
                        $("#toolbar").remove();
                        $.messager.alert("来自Crm系统",data.msg,"info");
                    }else{
                        $.messager.alert("来自Crm系统","操作失败","warning");
                    }
                }
            })
        }
    })
}

function delCusDevPlan() {
    var row = $("#dg").edatagrid("getSelected");
    if(row==null){
        $.messager.alert("来自Crm","请选择一条数据！","warning")
        return;
    }
    $.messager.confirm("来自Crm该系统","确认删除这条信息吗？",function (r) {
        if(r){
            $.ajax({
                url:ctx+"/cus_dev_plan/delete",
                data:"id="+row.id,
                dataType:'json',
                success:function (data) {
                    if(data.code==200){
                        $("#dg").edatagrid("load");
                        $.messager.alert("来自Crm","删除成功！","info")
                    }else{
                        $.messager.alert("来自Crm","删除失败！","error")
                    }
                }
            })
        }

    })

}
