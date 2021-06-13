function login() {
    console.log("click------>")
    $.ajax({
        url: "http://47.115.42.52:8080/captchaImage",
        type: "get",
        data: "json",
        success: function (data) {
            $("#imgCode").attr('src', data.img);
        }
    })
}