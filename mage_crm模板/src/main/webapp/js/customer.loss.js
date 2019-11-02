function searchCustomerLosses() {
    $("#dg").datagrid('load',{
        customerName: $("#customerName").val(),
        customerNum: $("#customerNum").val(),
        customerManager: $("#customerManager").val(),
        time: $("#time").val()
    })
}

/**
 * 预加载
 */
$(function () {
    $("#dg").datagrid({
        rowStyler:function (index,rowData) {
            if(rowData.state==0){
                return "background-color:yellow";
            }else if(rowData.state==1){
                return "background-color:red";
            }
        }
    })
})

function formatterState(val,rowData) {
    if(rowData.state==0){
        var href="javascript:openCustomerRepriDetailTab('客户流失暂缓处理_"+rowData.id+"',"+rowData.id+")";
        return "<a href="+href+">添加暂缓措施</a>"
    }
    if(rowData.state==1){
        var href="javascript:openCustomerRepriDetailTab('客户流失情况_"+rowData.id+"',"+rowData.id+")";
        return "<a href="+href+">查看流失详情</a>";
    }
}

function formatterOp(val) {
    if(val==0){
        return "暂缓流失";
    }else if(val==1){
        return "已流失";
    }else{
        return "未定义";
    }
}