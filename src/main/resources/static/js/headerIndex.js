// $(function () { // header.jsp의 위쪽 head에 script가 있어서 html body태그를 먼저 읽어야 해당 태그에 클래스를 추가 할 수 있어서 전부 로드 실행되는 ready() 사용이지만 대신 같은역할의 권장하는 $()사용
//     //alert(window.location.pathname);
//     //alert(window.location.pathname.replace(/\//g, ""));
//     let a = window.location.pathname.replace(/\//g, ""); // javascript엔 replaceall이 없어서 여러글자 바꾸려면 정규표현식으로 표현해야됨 선택자에 /가 들어가면 인식을 못해서 /다 제거하는 작업
//     // /는 "라고 보면되고 \/는 /문자열로 쓰기위해 구분한거 g는 모든걸 바꾸겠다는것 즉 replaceAll("\/", "") 이거랑 같은뜻
//     $("#" + a).parent().addClass("active"); // navbar에서 현재 페이지항목 active 상태로 만들기 (a태그의 parent인 li를 active로 바꾸는거 a태그 바꿔도 활성화로 표시됨)
// })

$((function () { // header.jsp의 위쪽 head에 script가 있어서 html body태그를 먼저 읽어야 해당 태그에 클래스를 추가 할 수 있어서 전부 로드 실행되는 ready() 사용이지만 대신 같은역할의 권장하는 $()사용
        $("a").on("click", function () {
            link = $(this).attr("id"); //여기선 this로 html문서에 있는 클릭한 a태그를 찾는거니 화살표함수가 아닌 function으로 사용 (user.js에 this 바인딩 설명 써놓음)
            $.ajax({
                method: 'GET',
                url: link,
                dataType: 'html',
            })
                .done(function (response) {
                    $('#bodyContents').children().remove(); // ajax로 본문만 갈아낄거니 현재 header.jsp의 bodyContents에 있는 요소 제거
                    $("#bodyContents").html(response); // ajax로 받아온 본문 내용을 header.jsp의 bodyContents에 html로 추가

                    $(".navbar-nav").find(".active").removeClass("active"); // navbar에서 이전 페이지항목 active 상태 제거

                    //만약 /같은 특수문자를 선택자에서 쓰고싶다면 역슬래쉬2개 \\를 앞에 넣어서 표현해야만 인식됨
                    idName = link.replace(/\//g, "\\/");// javascript엔 replaceall이 없어서 여러글자 바꾸려면 정규표현식으로 표현해야됨 선택자에 /가 들어가면 인식을 못해서 /다 제거하는 작업 /는 "라고 보면되고 \/는 /문자열로 쓰기위해 구분한거 g는 모든걸 바꾸겠다는것 즉 replaceAll("\/", "") 이거랑 같은뜻
                    $("#" + idName).parent().addClass("active"); // navbar에서 현재 페이지항목 active 상태로 만들기 (a태그의 parent인 li를 active로 바꾸는거 a태그 바꿔도 활성화로 표시됨)

                })
                .fail(function () {

                });
        });

        if(window.location.pathname == "/") // 처음 홈페이지 들어가면 header만 출력되면 안되니 메인페이지 index를 ajax로 요청해서 장갈이 해줌
        {
            $.ajax({
                method: 'GET',
                url: '/index',
                dataType: 'html',
            })
                .done(function (response) {
                    $('#bodyContents').children().remove(); // ajax로 본문만 갈아낄거니 현재 header.jsp의 bodyContents에 있는 요소 제거
                    $("#bodyContents").html(response); // ajax로 받아온 본문 내용을 header.jsp의 bodyContents에 html로 추가

                    $(".navbar-nav").find(".active").removeClass("active"); // navbar에서 이전 페이지항목 active 상태 제거
                })
                .fail(function () {

                });
        }

    })
)



