package com.cro.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

    @GetMapping({"", "/"})
    public String headerIndex() { // 첫 홈페이지 요청 컨트롤러로 본문에 메인페이지 index를 포함한 header 요청
        return "layout/headerIndex";
    }

    @GetMapping("index")
    public String index() { // 홈페이지 이용중 메인페이지로 이동시 ajax로 본문만 갈아끼우기 위한 컨트롤러
        return "index";
    }
}
