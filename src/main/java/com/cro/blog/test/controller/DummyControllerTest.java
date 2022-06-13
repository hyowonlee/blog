package com.cro.blog.test.controller;

import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.function.Supplier;

@Controller
public class DummyControllerTest {

    @Autowired
    public DummyControllerTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserRepository userRepository;

    @GetMapping("/dummy/join")
    public String testView()
    {
        return "test";
    }

    @PostMapping("/dummy/join")
    @ResponseBody
    public String testJoin(User user) // view에서 넘어오는 데이터들을 객체로도 받을수있다 데이터에 대한 필드가 다 존재하기 때문
    {
        System.out.println("id: "+user.getId());
        System.out.println("username: "+user.getUsername());
        System.out.println("password: "+user.getPassword());
        System.out.println("email: "+user.getEmail());
        System.out.println("role: "+user.getRole());
        System.out.println("createdate: "+user.getCreateDate());
        System.out.println("updatedate: "+user.getUpdateDate());

        user.setRole(RoleType.USER); // enum값 으로 세팅

        userRepository.save(user); // db에 insert

        return "DummyControllerTest.testJoin";
    }
    //객체가 아닌 변수로 받기
//    @PostMapping("/dummy/join")
//    public void testJoin(String username, String password, String email) // view에서 태그의 속성인 name과 변수명이 같다면 @RequestParame("username") 필요없음
//    {
//        System.out.println(id);
//        System.out.println(password);
//    }





    @GetMapping("/dummy/search")
    @ResponseBody
    public User testSearchGet(int id)
    {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다");
            }
        }); // null 반환 시 에러 반환

        System.out.println("DummyControllerTest.testSearchGet");

        return user;
    }

    @GetMapping("/dummy/search/{id}")
    @ResponseBody
    public User testSearchGet2(@PathVariable int id)
    {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 사용자가 없습니다");
            }
        }); // null 반환 시 에러 반환

        System.out.println("DummyControllerTest.testSearchGet2");

        return user;
    }

}
