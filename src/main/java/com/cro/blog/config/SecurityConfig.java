package com.cro.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

//이 3개의 어노테이션은 시큐리티 설정파일의 세트로 같이 오면 됨
@Configuration // 빈등록
@EnableWebSecurity // 해당클래스가 시큐리티 필터애 등록이된다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 페이지 요청보다 권한 및 인증을 미리 체크하겠다고 true로 설정한것
public class SecurityConfig {

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // 이거 안걸면 post 요청 안됨
            .authorizeRequests() // 요청이 들어오면
            .antMatchers("/auth/**", "/", "/index", "/css/**", "/img/**", "/js/**") // /auth로 들어오는건
            .permitAll() // 모든 사람에게 허용
            .anyRequest() // 이 이외의 요청은
            .authenticated() // 인증이 필요함
        .and()
            .formLogin();

        return http.build();
    }

}
