package com.cro.blog.service;

import com.cro.blog.model.User;
import com.cro.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    // 회원가입
    // exception시 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것
    @Transactional // 이 회원가입 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
    public void join(User user)
    {
        userRepository.save(user); // db에 insert
    }
}
