function signup() {
    var str = {
        "phone": $("#phone").val(),
        "password": $("#password").val(),
        "nickName": $("#nickName").val(),
        "email": $("#email").val(),
        "resoult": $("#captcha").val(),
        "questionId": $("#questionId").val(),
    };
    console.log(str)
    // 开启跨域处理
    jQuery.support.cors = true;
    $.ajax({
        url: "http://127.0.0.1:8888/signup",
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
            // 将token存到localStorage里
            localStorage.token = data.token;
            window.location.href = "http://127.0.0.1:8080/home/" + localStorage.token
        }
    })
}