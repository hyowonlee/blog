package com.cro.blog.controller;

import com.cro.blog.config.auth.PrincipalDetail;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/auth/joinForm")
    public String joinForm()
    {
        return "user/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm()
    {
        return "user/loginForm";
    }

    // 시큐리티 로그인 용 로그인 페이지 컨트롤러
    @GetMapping("/auth/securityLoginForm") // 시큐리티에서 자동으로 로그인페이지로 보낼때 헤더랑 context를 ajax로 같이 로딩해야되니 컨트롤러에선 헤더를 로딩하고 js에서 context 로딩
    public String securityLoginForm()
    {
        return "layout/headerIndex";
    }

    @GetMapping("/user/updateForm")
    public String updateForm()
    {
        return "user/updateForm";
    }
}
