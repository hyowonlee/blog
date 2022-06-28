let index = { // javascript 객체
    //javascript의 key: value
    init: function () {
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-checkUsername").on("click", () => {
            this.checkUsername();
        })
    },

    // 회원가입
    save: function () {
        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //console.log(data);

        //ajax 호출시 기본이 비동기 호출이라 다른기능들과 동시에 수행 가능
        $.ajax({
            type: 'POST',
            url: '/api/user/join',
            data: JSON.stringify(data), // 객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환
            contentType: "application/json; charset=utf-8", // 보낼 데이터의 mime 타입 세팅 (body데이터가 어떤 타입인지 세팅)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 그 문자열이 어떤 타입인지 세팅(이렇게 써주면 json을 javascript 오브젝트로 변경해줌, 안써도 자동으로 json을 javascript객체로 변환되긴함)
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("회원가입이 완료되었습니다");
                location.href = "/";
            } else {
                alert("회원가입 실패");
            }
        }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
        });
    },

    // 회원가입 - username 중복확인
    checkUsername: function () {
        $.ajax({
            type: 'POST',
            url: '/api/user/join/checkusername',
            data: $('#input-username').val(),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            if (response.data == 1) { // 서버로 부터 정상 return일 경우 사용가능 username
                alert("사용가능한 username 입니다");
                $('#btn-save').attr("disabled", false); // 버튼 활성화,비활성화
                $('#input-username').attr("disabled", true); // 버튼 활성화,비활성화
            } else {
                alert("사용중인 username 입니다");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    }
}

index.init(); // index자체는 그냥 object이기에 객체의 init을 호출해줘야 function이 작동