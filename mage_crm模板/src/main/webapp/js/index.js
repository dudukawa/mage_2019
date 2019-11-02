function userLogin() {
    var userName = $("#userName").val();
    var userPwd = $("#userPwd").val();
    console.log(userName+"  "+userPwd);
    if(isEmpty(userName)){
        alert("用户名不能为空");
        return;
    }
    if (isEmpty(userPwd)){
        alert("用户密码不能为空");
        return;
    }
    //封装参数
    var params = {};
    params.userName = userName;
    params.userPwd = userPwd;
    $.ajax({
        type:'post',
        url:ctx+'/user/login',
        data:params,
        dataType:'json',
        success:function (data) {
            console.log(data.code+" sdfdis"+data.msg);
            if(data.code == 200){
                alert("登录成功");
                //登陆成功存放数据到cookie
                $.cookie("id",data.result.id);
                $.cookie("userName",data.result.userName);
                $.cookie("trueName",data.result.trueName);
                //跳转页面
                window.location.href="main";
            }else{
                alert(data.msg);
            }
        }
    })
}