package com.cro.blog.controller;

import com.cro.blog.config.auth.PrincipalDetail;
import com.cro.blog.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

// 스프링 시큐리티에서 세션 가져오려면 @AuthenticationPrincipal PrincipalDetail principalDetail이 매개변수로 세션 받아주면 됨 아니면 아래 주석처럼 di 해도 접근가능
//    @Autowired
//    private PrincipalDetail principalDetail; // 세션은 스프링에서 컨트롤러의 매개변수로 적어주면 자동으로 받을 수 있고 또는 세션객체는 스프링 컨테이너가 빈등록해 가지고있으면 @Autowired해서 받을 수 있다 (이 클래스는 빈등록 안해서 되는지 확인해봐야됨)

    private final BoardService boardService;

//    @GetMapping({"", "/"})
//    // 첫 홈페이지 요청 컨트롤러로 본문에 메인페이지 index를 포함한 header 요청
//    public String headerIndex(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC)Pageable pageable) // 페이징 하기위한 매개변수
//    {
//        //System.out.println("로그인 사용자 아이디:"+principalDetail);
//
//        model.addAttribute("boards", boardService.boardList(pageable)); // index페이지 요청할때 뿌려줄 글 목록 가져와서 Model을 사용해 view로 넘김
//        return "layout/headerIndex"; // 여기서 viewResolver가 작동하여 리턴되는 이름과 매핑되는 페이지와 model로 넘기는 정보를 들고 이동해줌
//    }

    @GetMapping({"", "/"})
    // 첫 홈페이지 요청 컨트롤러로 본문에 메인페이지 header 요청 index는 js 에서 요청
    public String headerIndex() // 페이징 하기위한 매개변수
    {
        return "layout/headerIndex"; // 여기서 viewResolver가 작동하여 리턴되는 이름과 매핑되는 페이지와 model로 넘기는 정보를 들고 이동해줌
    }

    @GetMapping("/index")
    public String index(Model model, @PageableDefault(size=3, sort="id", direction = Sort.Direction.DESC)Pageable pageable) // 페이징 하기위한 매개변수
    { // 홈페이지 이용중 메인페이지로 이동시 ajax로 본문만 갈아끼우기 위한 컨트롤러

        model.addAttribute("boards", boardService.boardList(pageable)); // index페이지 요청할때 뿌려줄 글 목록 가져와서 Model을 사용해 view로 넘김
        return "index";
    }

    @GetMapping("/auth/board/{id}")
    public String findById(@PathVariable int id, Model model)
    {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/detailForm";
    }

    @GetMapping("/board/saveForm")
    public String saveForm()
    {
        return "board/saveForm";
    }

    @GetMapping("/board/updateForm/{id}")
    public String updateForm(@PathVariable int id, Model model)
    {
        model.addAttribute("board", boardService.boardDetail(id));
        return "board/updateForm";
    }
}
