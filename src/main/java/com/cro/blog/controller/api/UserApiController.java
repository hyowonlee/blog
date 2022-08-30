package com.cro.blog.controller.api;

import com.cro.blog.config.auth.PrincipalDetail;
import com.cro.blog.dto.ResponseDto;
import com.cro.blog.model.Board;
import com.cro.blog.model.User;
import com.cro.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserApiController {

    private final AuthenticationManager authenticationManager; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입
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

    @PutMapping("/api/user/update")
    public ResponseDto<Integer> update(@RequestBody User user)
    {
        System.out.println("UserApiController.update()");
        userService.userUpdate(user);
        // 여기서 트랜잭션 종료돼서 db에는 값 저장되지만 세션값은 안바뀌어서 바로 밑에서 우리가 직접 바꿔줄것 (강제로 세션을 만들거 : Authentication 객체 만들어서 세션에 집어넣을것)

        Authentication authentication =
                authenticationManager. // AuthenticationManager가 username~토큰을 가지고 세션(Authentication)을 만들어줌
                        authenticate( // 세션 생성
                        new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()) // id,pw로 username~ 토큰 객체 생성 이 토큰 객체를 가지고 AuthenticationManager가 세션생성
                        // 우리 db에 들어있는 암호는 해쉬화 되어있는데 우리 암호화 해쉬함수가 SecurityConfig.java에 빈 등록되어있어서 AuthenticationManager가 어떤 해쉬함수로 암호화한지 알고있음
                        // 그래서 여기선 해쉬화된 암호말고 평문 암호를 넣어도 AuthenticationManager가 알아서 빈등록된 해쉬함수로 해쉬화해서 확인해줌
                );
        SecurityContextHolder.getContext().setAuthentication(authentication); //만들어진 Authentication객체를 시큐리티 컨택스트에 집어넣어서 세션 등록

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

//    @Autowired
//    private HttpSession session; // 세션은 스프링에서 컨트롤러의 매개변수로 적어주면 자동으로 받을 수 있고 또는 세션객체는 스프링 컨테이너가 빈등록해 가지고있으니 @Autowired해서 받을 수 있다 (HttpSession)
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
