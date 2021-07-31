function login() {
    var str = {
        "phone": $("#loginPhone").val(),
        "password": $("#loginPassword").val(),
        "note": $("#loginNote").val(),
    };
    console.log(str)

    // 开启跨域处理
    jQuery.support.cors = true;
    $.ajax({
        url: "http://127.0.0.1:8888/login",
        dataType: "json",
        contentType: "application/json;charset=UTF-8",
        Accept: "*/*",
        type: "POST",
        data: JSON.stringify(str),
        headers: {
            // 设置header头
            "Accept": "application/json",
        },
        success(data) {
            if(data.code == 1){
                // 将token存到localStorage里
                localStorage.token = data.token;
                window.location.href = "http://127.0.0.1:8080/home/" + localStorage.token
            } else {
                window.alert(data.error)
            }
        },error(){
            window.alert("system error !!! ")
        }
    })
}