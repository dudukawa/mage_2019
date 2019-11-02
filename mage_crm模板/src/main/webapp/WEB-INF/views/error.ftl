<#include "common.ftl">
<script>
    $(function () {
        alert("${msg}");
        if("${uri}" == "/main"){
            window.location.href = ctx+"/index";//ctx在common.ftl页面已定义为域对象"${ctx}"所有可以直接写ctx
        }else{
            window.parent.location.href = ctx+"/index";
        }
    })
</script>