function getCapture() {
    console.log("getCapture click======>")
    $.ajax({
        url: "http://127.0.0.1:8888/captchaImage",
        type: "get",
        /*dataType: "json",*/
        success(data) {
            $("#imgCode").attr('src', data.img);
            $("#questionId").attr('value', data.qId);
            localStorage.setItem("questionId",data.qId);
        },
        error(){
            alert("error????");
        }
    })
}