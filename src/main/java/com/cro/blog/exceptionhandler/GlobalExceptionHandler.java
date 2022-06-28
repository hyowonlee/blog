package com.cro.blog.exceptionhandler;

import com.cro.blog.dto.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

//Exception 처리하는 class
@ControllerAdvice //어디서든 Exception 발생시 이 클래스로 오게하는 어노테이션, 즉 모든 Exception 발생시 다 이쪽으로 들어오게됨, 여기서 해당 Exception을 처리하겠다
@RestController
public class GlobalExceptionHandler {

//    // java IllegalArgumentException 발생시 이 함수를 실행 시킬 것
//    @ExceptionHandler(value = IllegalArgumentException.class) // IllegalArgumentException이 발생하면 이 함수 호출, 그 에러객체를 여기 매개변수로 전달해줄것
//    public String handleArgumentException(IllegalArgumentException e)
//    {
//        return "<h1>" + e.getMessage() + "</h1>";
//    }

    @ExceptionHandler(value = Exception.class) // 일단 강의에서 이렇게해서 모든 Exception에 대해서 처리하도록 여기다 해놓긴 할건데 나중에 바꾸자
    public ResponseDto<String> handleArgumentException(Exception e)
    {
        return new ResponseDto<String>(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
        //return "<h1>" + e.getMessage() + "</h1>";
    }
}
