package com.cro.blog.service;

import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    private final BCryptPasswordEncoder bCryptPasswordEncoder; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입 (원본 객체는 SecurityConfig.java에 있음)

    // 회원가입
    // exception시 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것
    @Transactional // 이 회원가입 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
    public void join(User user)
    {
        String rawPassword = user.getPassword(); // 비밀번호 원문

        String encPassword = bCryptPasswordEncoder.encode(rawPassword); // 해쉬화된 비밀번호

        user.setPassword(encPassword);

        user.setRole(RoleType.USER); // view에서 안가져온 데이터 세팅

        userRepository.save(user); // db에 insert
    }

    // 회원가입 - username 중복확인
    @Transactional(readOnly = true) // readOnly옵션을통해 select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션을 종료해 데이터 정합성을 유지해줌
    // select는 값 변경이 없으니 트랜잭션 안붙여도 되지 않나? 라고 생각할 수 있지만 데이터의 정합성을 위해 select도 붙인다,
    // 만약 한 기능에서 select를 2번해야되는데 1번 select하고 중간에 다른곳에서 값이 수정되서 commit될 경우 2번째 select에선 값이 바뀔수 있으니 select문에도 transactional을 붙여 트랜잭션으로 만들면 항상 내 실행시점 이전의 데이터를 들고와줌, 그래서 같은데이터를 들고와 정합성을 유지해줌
    public boolean checkUsername(String username)
    {
        return userRepository.existsByUsername(username);
    }


//    // 시큐리티를 사용하지 않은 전통적 로그인
//    @Transactional(readOnly = true) // readOnly옵션을통해 select 할때 트랜잭션 시작, 서비스 종료시에 트랜잭션을 종료해 데이터 정합성을 유지해줌
//    public User login(User user)
//    {
//        return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
//    }
}
