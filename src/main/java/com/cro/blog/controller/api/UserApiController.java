package com.cro.blog.controller.api;

import com.cro.blog.dto.ResponseDto;
import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    //회원가입
    @PostMapping("/auth/api/user/join")
    public ResponseDto<Integer> join(@RequestBody User user)
    {
        System.out.println("UserApiController.join()");

        userService.join(user); // 만약 에러나면 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공시 1 리턴
    }

    // 회원가입 - username 중복확인
    @PostMapping("/auth/api/user/join/checkusername")
    public ResponseDto<Integer> checkUsername(@RequestBody String username)
    {
        System.out.println("UserApiController.checkUsername()");

        if(!userService.checkUsername(username)) // 없는 username은 1 return
        {
            return new ResponseDto<Integer>(HttpStatus.OK.value(),1); // user.js checkUsername에서 data값으로 검증
        }
        else // username 이미 있으면 -1 return
        {
            return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1);
        }
    }

//    @Autowired
//    private HttpSession session; // 세션은 스프링에서 컨트롤러의 매개변수로 적어주면 자동으로 받을 수 있고 또는 세션객체는 스프링 컨테이너가 빈등록해 가지고있으니 @Autowired해서 받을 수 있다 (HtpSession)
//
//    // 스프링 시큐리티 안쓰는 일반 로그인
//    @PostMapping("api/user/login")
//    public ResponseDto<Integer> login(@RequestBody User user, HttpSession session) //di된 session객체로도 받을 수 있지만 여기선 그냥 매개변수로 세션 받아봄 세션에 대한 설명은 밑에 적힘
//    {
//        System.out.println("UserApiController.login()");
//        User principal = userService.login(user); //principal은 접근주체라는 용어로 많이 쓰인다
//
//        if(principal != null) // 해당 사용자가 있으면 세션을 만들어주면 되는데 세션은 스프링에서 컨트롤러의 매개변수로 적어주면 자동으로 받을 수 있고 또는 세션객체는 스프링 컨테이너가 빈등록해 가지고있으니 @Autowired해서 받을 수 있다 (HttpSession)
//        {
//            session.setAttribute("principal", principal); // 키값 "principal"로 세팅해서 principal 변수로 세션 설정 (key를 사용해 객체를 세션에 바인딩)
//            return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); // 성공시 1 리턴
//        }
//        else // 사용자 없으면 -1 return
//        {
//            return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), -1);
//        }
//
//    }
}
