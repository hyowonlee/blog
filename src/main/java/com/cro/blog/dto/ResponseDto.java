package com.cro.blog.dto;

import lombok.*;
import org.springframework.http.HttpStatus;

// api 리턴을 위한 Dto 클래스로 자바에서 api 응답에 대한 return 시 http status code와 data를 리턴하기 위한 클래스로 이 클래스로 리턴해서 데이터와 http status code 리턴
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseDto<T> {
    int status; // return할 http status code
    T data; // return할 data
}
