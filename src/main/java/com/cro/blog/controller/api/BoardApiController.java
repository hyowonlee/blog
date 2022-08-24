package com.cro.blog.controller.api;

import com.cro.blog.config.auth.PrincipalDetail;
import com.cro.blog.dto.ResponseDto;
import com.cro.blog.model.Board;
import com.cro.blog.model.User;
import com.cro.blog.service.BoardService;
import com.cro.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    // 글 저장
    @PostMapping("/api/board/save")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principalDetail) //스프링 시큐리티에서 세션 가져오려면 @AuthenticationPrincipal PrincipalDetail principalDetail이 매개변수로 세션 받아주면 됨 아니면 아래 주석처럼 di 해도 접근가능
    {
        System.out.println("BoardApiController.save()");
        boardService.save(board, principalDetail.getUser()); // 게시물에 user 정보가 필요하니 세션에서 가져온 user객체 사용
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공시 1 리턴
    }

    @DeleteMapping("/api/board/delete/{id}")
    public ResponseDto<Integer> delete(@PathVariable int id)
    {
        System.out.println("BoardApiController.delete()");
        boardService.boardDelete(id);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공시 1 리턴
    }
}
