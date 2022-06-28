package com.cro.blog.controller.api;

import com.cro.blog.dto.ResponseDto;
import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    //회원가입
    @PostMapping("/api/user/join")
    public ResponseDto<Integer> join(@RequestBody User user)
    {
        System.out.println("UserApiController.join()");

        user.setRole(RoleType.USER); // view에서 안가져온 데이터 세팅

        userService.join(user); // 만약 에러나면 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공시 1 리턴
    }
}