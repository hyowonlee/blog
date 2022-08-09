package com.cro.blog.config.auth;

import com.cro.blog.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 스프링시큐리티가 로그인요청을 가로채서 로그인을 진행하고 완료가 되면 userDetails 타입의 오브젝트를
// 스프링 시큐리티의 고유한 세션저장소에 저장을 해줌
public class PrincipalDetail implements UserDetails {
    private User user; // 컴포지션 : 객체를 클래스 안에 품고있는걸 컴포지션이라함

    public PrincipalDetail(User user)
    {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지 리턴 (true: 만료안됨)
    // 이것들이 true여야 spring이 만료 안되어있다고 인식해서 로그인해줌 false 리턴해주면 로그인 안됨
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠긴지 아닌지 리턴 (true: 만료안됨)
    // 이것들이 true여야 spring이 만료 안되어있다고 인식해서 로그인해줌 false 리턴해주면 로그인 안됨
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 비밀번호가 만료되지 않았는지 리턴 (true: 만료안됨)
    // 이것들이 true여야 spring이 만료 안되어있다고 인식해서 로그인해줌 false 리턴해주면 로그인 안됨
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정이 활성화되어있는지 리턴 (true: 만료안됨)
    // 이것들이 true여야 spring이 만료 안되어있다고 인식해서 로그인해줌 false 리턴해주면 로그인 안됨
    @Override
    public boolean isEnabled() {
        return true;
    }

    // 계정이 가진 권한목록을 리턴 권한이 여러개일 경우 루프 돌아야됨
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collections = new ArrayList<>();

//        collections.add(new GrantedAuthority() { //인터페이스라 익명클래스가 만들어지면서
//            @Override
//            public String getAuthority() { //추상메서드 오버라이딩
//                return "ROLE_"+user.getRole(); // 스프링에서 권한을 리턴할때 앞에 ROLE_ 이걸 꼭 붙여야되는 규칙이 있음
//            }
//        });
        // 자바에선 함수를 넘길 수 없으니 함수를 넘기기 위해 위처럼 익명클래스로 만들어준건데 람다를 사용하면 좀 더 간단히 처리가능 어차피 여기 들어올수 있는건 GrantedAuthority클래스이고
        // 이 클래스에는 함수 하나밖에 없으니 람다식이 그 함수로 인식되어서 그냥 return 만 써주면 됨
       collections.add(()->{ return "ROLE_"+user.getRole(); });

        return collections;
    }
}
