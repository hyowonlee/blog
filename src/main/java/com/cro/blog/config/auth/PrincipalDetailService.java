package com.cro.blog.config.auth;

import com.cro.blog.model.User;
import com.cro.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrincipalDetailService implements UserDetailsService {

    private final UserRepository userRepository; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    // 스프링 시큐리티에서 로그인 요청을 가로채 로그인이 진행될때 이 loadUserByUsername 함수가 자동으로 실행되는데
    // jsp 페이지의 name이 username, password 인 2개의 데이터를 가로채는데 password 부분 처리는 알아서 하고
    // 우리는 오버라이딩해서 username이 DB에 있는지만 확인해주면 됨
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       User principal = userRepository.findByUsername(username).orElseThrow(()->{
           return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다.:"+username);
       });
       return new PrincipalDetail(principal); // 이때 UserDetails 타입의 유저객체를 리턴하여 시큐리티의 세션에 유저정보가 저장됨,
       // 여기에 우리가 만든 user객체가 들어간 UserDetails 객체가 안들어가면 스프링 시큐리티 기본 id(user) pw(콘솔창에 뜨는거) 그게 들어가게됨
    }
}
