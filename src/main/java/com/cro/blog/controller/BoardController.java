package com.cro.blog.controller;

import com.cro.blog.config.auth.PrincipalDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

//    @Autowired
//    private PrincipalDetail principalDetail; // 세션은 스프링에서 컨트롤러의 매개변수로 적어주면 자동으로 받을 수 있고 또는 세션객체는 스프링 컨테이너가 빈등록해 가지고있으면 @Autowired해서 받을 수 있다 (이클래스는 빈등록 안해서 되는지 확인해봐야됨)

    @GetMapping({"", "/"})
    // 첫 홈페이지 요청 컨트롤러로 본문에 메인페이지 index를 포함한 header 요청
    public String headerIndex(@AuthenticationPrincipal PrincipalDetail principalDetail) { // 스프링 시큐리티에서 세션 가져오려면 @AuthenticationPrincipal이 매개변수로 세션 받아주면 됨 아니면 위 주석처럼 di 해도 접근가능
        System.out.println("로그인 사용자 아이디:"+principalDetail);
        return "layout/headerIndex";
    }

    @GetMapping("/index")
    public String index()
    { // 홈페이지 이용중 메인페이지로 이동시 ajax로 본문만 갈아끼우기 위한 컨트롤러
        return "index";
    }
}
