var index = { // javascript 객체 ajax때문에 페이지마다 여러번 선언되어서 같은이름 여러번 선언 가능한 var로 했음 let은 안됨
    //javascript의 key: value
    init: function () {

        //화살표함수를 쓰는 이유는 this를 바인딩하기 위해서임 코드를 줄이기 위해서가 아님 여기서 function(){}로 사용하면 this가 이 index변수가 아닌 윈도우객체를 가리킴
		//만약 꼭 function으로 써야겠다 한다면 위의 index 시작부분에서 let _this = this 처럼 index를 가리키는 this를 만들어서 그거로  function(){} 이 안에서 사용해야됨
        //화살표 함수를 쓰면 화살표함수의 바깥쪽 this가 this가 된다 = > 호출할 때 정해진다 그래서 여기선 밖인 index변수를 this가 가리키는것 만약 객체가 아닌 최상단에서 화살표함수를 쓰면
        //웹브라우저인 window객체를 가리킬것 최상단에서 function(){}을 쓰면 그 html문서인 document객체를 가리킬것
        $("#btn-save").on("click", () => {
            this.save();
        });

        $("#btn-delete").on("click", () => {
            this.delete();
        });

        $("#btn-update").on("click", () => {
            this.update();
        });

        $("#btn-reply-save").on("click", () => {
            this.replySave();
        });
    },

    // 글 저장
    save: function () {
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/board/save',
            data: JSON.stringify(data), // 객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환
            contentType: "application/json; charset=utf-8", // 보낼 데이터의 mime 타입 세팅 (body데이터가 어떤 타입인지 세팅)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 그 문자열이 어떤 타입인지 세팅(이렇게 써주면 json을 javascript 오브젝트로 변경해줌, 안써도 자동으로 json을 javascript객체로 변환되긴함)
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("글쓰기가 완료되었습니다");
                location.href = "/";
            } else {
                alert("글쓰기 실패");
            }
        }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
        });
    },
    // 글 삭제
    delete: function () {
        var id = $("#id").text();

        $.ajax({
            type: 'DELETE',
            url: '/api/board/delete/'+id,
            dataType: "json",
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("글삭제가 완료되었습니다");
                location.href = "/";
            } else {
                alert("글삭제 실패");
            }
        }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
        });
    },
    // 글 업데이트
    update: function () {
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val()
        };

        $.ajax({
            type: 'PUT',
            url: '/api/board/update/'+id,
            data: JSON.stringify(data),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("글수정이 완료되었습니다");
                location.href = "/";
            } else {
                alert("글수정 실패");
            }
        }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
        });
    },
    // 댓글 작성
    replySave: function () {
        let data = {
            content: $("#reply-content").val()
        };
        let boardId = $("#board-id").val();
        $.ajax({
            type: 'POST',
            url: `/api/board/${boardId}/reply`,
            data: JSON.stringify(data), // 객체를 전송하려면 객체 자체를 보내면 이해하지 못하니 javascript객체를 json(문자열)로 변환
            contentType: "application/json; charset=utf-8", // 보낼 데이터의 mime 타입 세팅 (body데이터가 어떤 타입인지 세팅)
            dataType: "json" // 요청을 서버로해서 응답이 왔을 때 그 문자열이 어떤 타입인지 세팅(이렇게 써주면 json을 javascript 오브젝트로 변경해줌, 안써도 자동으로 json을 javascript객체로 변환되긴함)
        }).done(function (response) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            if (response.data == 1) {
                alert("댓글작성이 완료되었습니다");

            // 댓글 작성 완료 후 ajax로 페이지 갈려고 여기서 다시 ajax 요청해서 페이지 가져와서 장갈이 함
            $.ajax({
                method: 'GET',
                url: `/auth/board/${boardId}`,
                dataType: 'html',
            })
                .done(function (response) {
                    $('#bodyContents').children().remove(); // ajax로 본문만 갈아낄거니 현재 header.jsp의 bodyContents에 있는 요소 제거
                    $("#bodyContents").html(response); // ajax로 받아온 본문 내용을 header.jsp의 bodyContents에 html로 추가

                    $(".navbar-nav").find(".active").removeClass("active"); // navbar에서 이전 페이지항목 active 상태 제거
                })
                .fail(function () {

                });

            } else {
                alert("댓글쓰기 실패");
            }
        }).fail(function (error) { // ajax요청해서 받은 응답이 여기 매개변수로 들어옴
            alert(JSON.stringify(error)); // alert에서 내용 보이려면 string 타입이여야돼서 변환
        });
    },
}

index.init(); // index자체는 그냥 object이기에 객체의 init을 호출해줘야 function이 작동