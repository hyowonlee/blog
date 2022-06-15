package com.cro.blog.test.controller;

import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
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
//    public void testJoin(@RequestParame("username") String username, String password, String email) // view에서 태그의 속성인 name과 변수명이 같다면 @RequestParame("username") 필요없음
//    {
//        System.out.println(id);
//        System.out.println(password);
//    }





    @GetMapping("/dummy/search")
    @ResponseBody
    public User testSearchGet(int id)
    {
//        User user = userRepository.findById(id).orElseThrow(() -> {
//            return new IllegalArgumentException("해당 사용자가 없습니다");
//        }); // 밑에있는거랑 같은데 람다식으로 익명클래스 처리해서 한거

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

    // 전체 유저 조회
    @GetMapping("/dummy/search/list")
    @ResponseBody
    public List<User> listTestSearchGet()
    {
        System.out.println("DummyControllerTest.listTestSearchGet");
        return userRepository.findAll();
    }

    // jpa는 페이징 할 수 있게 데이터를 select해주는 라이브러리 제공, jsp에서 하려면 우리가 로직을 다 짜야됨
    // 한페이지당 2건의 데이터를 리턴받아볼 예정
    @GetMapping("/dummy/search/list/page")
    @ResponseBody
    public List<User> listTestSearchGetPageList(@PageableDefault(size=2, sort="id", direction = Sort.Direction.DESC)Pageable pageable)
    {
        System.out.println("DummyControllerTest.listTestSearchGetPageList");
        //Page<User> pageList = userRepository.findAll(pageable); // Pageable 객체를 입력받는 함수로 간단히 페이징 가능

        // 위에껄로 받으면 데이터가 pageable데이터도 같이와서 지금은 필요없으니 content데이터만 가져오려고 .getContent()로 content데이터만 가져옴
        // Page 객체는 마지막페이지인지 첫 페이지인지 .isLast() .isFirst() 이런 함수들을 제공함
        Page<User> pageList = userRepository.findAll(pageable);
        List<User> userPageList = pageList.getContent(); // 리스트로 받아서 데이터만 리턴해주는게 좋다
        return userPageList;
    }

    //회원정보 update
    @PostMapping("/dummy/update")
    @ResponseBody
    public User testUpdatePost(User user)
    {
        User selectUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다");
        });

        selectUser.setUsername(user.getUsername());
        selectUser.setPassword(user.getPassword());
        selectUser.setEmail(user.getEmail());

        userRepository.save(selectUser); // save함수는 이미 있는 id는 update 시킴

        return selectUser;
    }

    //@Transactional
    @PostMapping("/dummy/jsonupdate")
    @ResponseBody
    public User testJsonUpdatePost(@RequestBody User user)
    {
        User selectUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다");
        });

        selectUser.setUsername(user.getUsername());
        selectUser.setPassword(user.getPassword());
        selectUser.setEmail(user.getEmail());

        //이부분을 주석처리하고 이 함수 어노테이션에 @Transactional 적어넣으면 더티체킹 때문에 .save()안써도 db 값이 update 됨
        userRepository.save(selectUser); // save함수는 이미 있는 id는 update 시킴

        return selectUser;
    }
}
