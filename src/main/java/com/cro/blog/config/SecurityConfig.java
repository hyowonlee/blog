package com.cro.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//이 3개의 어노테이션은 시큐리티 설정파일의 세트로 같이 오면 됨
@Configuration // 빈등록
@EnableWebSecurity // 해당클래스가 시큐리티 필터에 등록이된다
@EnableGlobalMethodSecurity(prePostEnabled = true) // 특정 주소로 접근 시 페이지 요청보다 권한 및 인증을 미리 체크하겠다고 true로 설정한것
public class SecurityConfig { //WebSecurityConfigurerAdapter가 deprecated 되어서 더이상 사용 못하고 SecurityFilterChain 를 빈으로 등록하는 방식을 사용

    @Bean // 이 함수가 리턴하는 BCryptPasswordEncoder객체를 빈 등록으로 IOC함
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    /*
    *
    * 원래 52강 강의에서는 AuthenticationManagerBuilder에 userDetailsService와 passwordEncoder를 세팅해주는 함수가 있었는데
    * WebSecurityConfigurerAdapter가 deprecated 되어서 오버라이드로 이용해야되는 configure 함수를 사용하지 못하기도 하고
    * UserDetailsService 및 PasswordEncoder가 빈으로 등록되어있으면 따로 설정하지 않아도 자동으로 해당 클래스로 세팅되는듯
    *
    * */


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf().disable() // 이거 안걸면 post 요청 안됨 (csrf 토큰 비활성화)
            .authorizeRequests() // 요청이 들어오면
            .antMatchers("/auth/**", "/", "/index", "/css/**", "/img/**", "/js/**") // /auth로 들어오는건
            .permitAll() // 모든 사람에게 허용
            .anyRequest() // 이 이외의 요청은
            .authenticated() // 인증이 필요함
        .and()
            .formLogin()
            .loginPage("/auth/securityLoginForm") // 스프링 시큐리티용 로그인 url
            .loginProcessingUrl("/auth/api/login") // 스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로재서 대신 로그인 수행함
            .defaultSuccessUrl("/") // 정상종료시 redirect
        .and()
            .logout()
            .logoutSuccessUrl("/")
            ;

        return http.build();
    }
}
