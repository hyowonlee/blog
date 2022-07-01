$(function () { // header.jsp의 위쪽 head에 script가 있어서 html body태그를 먼저 읽어야 해당 태그에 클래스를 추가 할 수 있어서 전부 로드 실행되는 ready() 사용이지만 대신 같은역할의 권장하는 $()사용
    //alert(window.location.pathname);
    //alert(window.location.pathname.replace(/\//g, ""));
    let a = window.location.pathname.replace(/\//g, ""); // javascript엔 replaceall이 없어서 여러글자 바꾸려면 정규표현식으로 표현해야됨 선택자에 /가 들어가면 인식을 못해서 /다 제거하는 작업
    // /는 "라고 보면되고 \/는 /문자열로 쓰기위해 구분한거 g는 모든걸 바꾸겠다는것 즉 replaceAll("\/", "") 이거랑 같은뜻
    $("#" + a).parent().addClass("active"); // navbar에서 현재 페이지항목 active 상태로 만들기 (a태그의 parent인 li를 active로 바꾸는거 a태그 바꿔도 활성화로 표시됨)
})


// $(document).ready( function (){
//
//   $("a").on("click", function () {
//     link = $(this).attr("href");
//     $.ajax({
//         method: 'GET',
//         url: link,
//     })
//         .done(function (html) {
//             //$("#" + link).empty().append(html);
//             alert(":123");
//             //여기선 this로 html문서에 있는 클릭한 a태그를 찾는거니 화살표함수가 아닌 function으로 사용 (user.js에 this 바인딩 설명 써놓음)
//
//             //$(".navbar-nav").find(".active").removeClass("active");
//             $("#" + link).parent().addClass("active");
//
//
//         })
//         .fail(function () {
//
//         });
// });
//
// });


// $(document).ready(function () {
//     //여기선 this로 html문서에 있는 클릭한 a태그를 찾는거니 화살표함수가 아닌 function으로 사용 (user.js에 this 바인딩 설명 써놓음)
//     $(".navbar-nav a").on("click", function () {
//         $(".navbar-nav").find(".active").removeClass("active");
//         $(this).parent().addClass("active");
//     });
//
// })



