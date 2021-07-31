function note() {
    var str={"phone": $("#loginPhone").val()};
    $.ajax({
        url: "http://127.0.0.1:8888/note",
        contentType: "application/json;charset=UTF-8",
        dataType: "json",
        type: "POST",
        data: JSON.stringify(str),
        success(note){
            window.alert("短信验证码是:" + note)
        },
    })
}