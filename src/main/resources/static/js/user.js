let index = { // javascript 객체
    //javascript의 key: value
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });
    },

    // 회원가입
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);

        $.ajax().done().fail();

        $.ajax({
            type: 'post',
            url: '/dummy/jsonupdate',
            data: JSON.stringify(data), /*객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환*/
            dataType: 'json',
            contentType: "application/json; charset=utf-8",
            success: function (data) {
                console.log(data)
            },
            error: function (request, status, error) {
                console.log(request.responseText);
            }
        });
    }
}

index.init(); // index자체는 그냥 object이기에 객체의 init을 호출해줘야 functino이 작동