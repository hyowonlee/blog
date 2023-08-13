var index = { // javascript 객체 ajax때문에 페이지마다 여러번 선언되어서 같은이름 여러번 선언 가능한 var로 했음 let은 안됨
    //javascript의 key: value
    init: function () {

        //화살표함수를 쓰는 이유는 this를 바인딩하기 위해서임 코드를 줄이기 위해서가 아님 여기서 function(){}로 사용하면 this가 이 index변수가 아닌 윈도우객체를 가리킴
		//만약 꼭 function으로 써야겠다 한다면 위의 index 시작부분에서 let _this = this 처럼 index를 가리키는 this를 만들어서 그거로  function(){} 이 안에서 사용해야됨
        //화살표 함수를 쓰면 화살표함수의 바깥쪽 this가 this가 된다 = > 호출할 때 정해진다 그래서 여기선 밖인 index변수를 this가 가리키는것 만약 객체가 아닌 최상단에서 화살표함수를 쓰면
        //웹브라우저인 window객체를 가리킬것 최상단에서 function(){}을 쓰면 그 html문서인 document객체를 가리킬것 (궁금하면 this 바인딩 검색)
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-checkUsername").on("click", () => {
            this.checkUsername();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        // $("#btn-login").on("click", () => {
        //     this.login();
        // });
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
            url: '/auth/api/user/join',
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

    //회원수정
    update: function () {
        if($('#password').val()=='' || $('#email').val()=='')
        {
            alert("모든 정보를 입력해주시기 바랍니다.");
            return ;
        }

        let data = {
            username: $("#username").val(),
            password: $("#password").val(),
            email: $("#email").val()
        };

        //ajax 호출시 기본이 비동기 호출이라 다른기능들과 동시에 수행 가능
        $.ajax({
            type: 'PUT',
            url: '/api/user/update',
            data: JSON.stringify(data), // 객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환
            contentType: "application/json; charset=utf-8", // 보낼 데이터의 mime 타입 세팅 (body데이터가 어떤 타입인지 세팅)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 그 문자열이 어떤 타입인지 세팅(이렇게 써주면 json을 javascript 오브젝트로 변경해줌, 안써도 자동으로 json을 javascript객체로 변환되긴함)
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("회원수정이 완료되었습니다");
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
            url: '/auth/api/user/join/checkusername',
            data: $('#username').val(),
            //data: $('#input-username').val(),
            contentType: "application/json; charset=utf-8",
            dataType: "json"
        }).done(function (response) {
            if (response.data == 1) { // 서버로 부터 정상 return일 경우 사용가능 username
                alert("사용가능한 username 입니다");
                $('#btn-save').attr("disabled", false); // 버튼 활성화,비활성화
                $('#username').attr("disabled", true); // 버튼 활성화,비활성화
                //$('#input-username').attr("disabled", true); // 버튼 활성화,비활성화
            } else {
                alert("사용중인 username 입니다");
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        })
    },

    // // 스프링 시큐리티 안쓰는 일반 로그인
    // login: function () {
    //     let data = {
    //         username: $("#username").val(),
    //         password: $("#password").val()
    //     };
    //
    //     //ajax 호출시 기본이 비동기 호출이라 다른기능들과 동시에 수행 가능
    //     $.ajax({
    //         type: 'POST',
    //         url: '/api/user/login',
    //         data: JSON.stringify(data), // 객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환
    //         contentType: "application/json; charset=utf-8", // 보낼 데이터의 mime 타입 세팅 (body데이터가 어떤 타입인지 세팅)
    //         dataType: "json" // 요청을 서버로해서 응답이 왔을 때 그 문자열이 어떤 타입인지 세팅(이렇게 써주면 json을 javascript 오브젝트로 변경해줌, 안써도 자동으로 json을 javascript객체로 변환되긴함)
    //     }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
    //         if (response.data == 1) {
    //             //alert("로그인이 완료되었습니다");
    //             location.href = "/";
    //         } else {
    //             alert("로그인 실패");
    //         }
    //     }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
    //         alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
    //     });
    // },
}

index.init(); // index자체는 그냥 object이기에 객체의 init을 호출해줘야 function이 작동
