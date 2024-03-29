package com.cro.blog.test.controller;

import com.cro.blog.model.Board;
import com.cro.blog.model.RoleType;
import com.cro.blog.model.User;
import com.cro.blog.repository.BoardRepository;
import com.cro.blog.repository.UserRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;
import java.util.function.Supplier;

@Controller
public class DummyControllerTest {

    @Autowired
    BoardRepository boardRepository;

    //생성자 주입
    @Autowired
    public DummyControllerTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    private UserRepository userRepository;

    @GetMapping("/dummy/board/{id}")
    @ResponseBody
    public Board getBoard(@PathVariable int id)
    {
        return boardRepository.findById(id).get();
    }

    @GetMapping("/dummy/join")
    public String testView()
    {
        return "test";
    }

    @PostMapping("/dummy/join")
    @ResponseBody
    @Transactional // db의 하나의 작업단위로 어노테이션 적용시 작업 오류시 롤백이 가능함 그래서 db데이터가 변경되는 작업은 이걸 붙여주는게 좋다
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

        if(userRepository.existsByUsername(user.getUsername())) //username 중복 확인, User.java에 username unique를 걸어놓긴 했음 근데 existsBy 때문에 넣어봄
        {
            throw new IllegalArgumentException("중복된 username");
        }

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
    @Transactional // db의 하나의 작업단위로 어노테이션 적용시 작업 오류시 롤백이 가능함 그래서 db데이터가 변경되는 작업은 이걸 붙여주는게 좋다
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

    @PostMapping("/dummy/jsonupdate")
    @ResponseBody
    @Transactional // db의 하나의 작업단위로 어노테이션 적용시 작업 오류시 롤백이 가능함 그래서 db데이터가 변경되는 작업은 이걸 붙여주는게 좋다
    public User testAjaxJsonUpdatePost(@RequestBody User user) // json 데이터로 들어오니 @RequestBody를 통해 들어온 json 데이터를 메시지 컨버터인 jackson 라이브러리로 java 객체로 변환
    {
        User selectUser = userRepository.findByUsername(user.getUsername()).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다");
        });

        selectUser.setUsername(user.getUsername());
        selectUser.setPassword(user.getPassword());
        selectUser.setEmail(user.getEmail());

        //이부분을 주석처리하고 이 함수 어노테이션에 @Transactional 적어넣으면 더티체킹 때문에 .save()안써도 db 값이 update 됨
        //userRepository.save(selectUser); // save함수는 이미 있는 id는 update 시킴

        return selectUser;
    }

//    @ResponseBody
//    @DeleteMapping("/dummy/delete")
//    public User delete(String username)
//    {
//        User deleteUser = userRepository.deleteByUsername(username).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자가 없습니다");
//        });
//
//        return deleteUser;
//    }



//    @ResponseBody
//    @DeleteMapping("/dummy/delete")
//    public User delete(String username)
//    {
//        User selectUser = userRepository.findByUsername(username).orElseThrow(()->{
//            return new IllegalArgumentException("해당 사용자가 없습니다");
//        });
//
//        userRepository.deleteByUsername(username);
//
//        return selectUser;
//    }

    @ResponseBody
    @DeleteMapping("/dummy/delete/{username}")
    @Transactional // db의 하나의 작업단위로 어노테이션 적용시 작업 오류시 롤백이 가능함 그래서 db데이터가 변경되는 작업은 이걸 붙여주는게 좋다
    public String ajaxJsonDelete(@PathVariable String username)
    {
        User selectUser = userRepository.findByUsername(username).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다"); // view단에서 ajax로 처리하기에 에러가 view단의 ajax 요청함수의 return으로 들어감 (test.jsp의 error: function (request, status, error) 이쪽으로 리턴이 들어감)
        });


        // 사실 이 try catch는 위에서 없는 사용자에 대한 예외처리를 해주니 여기선 에러로 들어가진 않을것
        try
        {
            userRepository.deleteById(selectUser.getId());
        }
        catch (EmptyResultDataAccessException e)
        {
            return "delete error: " + e; // view단에서 ajax로 처리하기에 에러가 view단의 ajax 요청함수의 return으로 들어감 (test.jsp의 error: function (request, status, error) 이쪽으로 리턴이 들어감)
        }

        return "delete username = " + username; // view단에서 ajax로 처리하기에 결과가 view단의 ajax 요청함수의 return으로 들어감 (test.jsp의 error: success: function (response) 이쪽으로 리턴이 들어감)
    }

    @ResponseBody
    @DeleteMapping("/dummy/delete")
    @Transactional
    public User testDelete(String username)
    {
        User selectUser = userRepository.findByUsername(username).orElseThrow(()->{
            return new IllegalArgumentException("해당 사용자가 없습니다");
        });

        userRepository.deleteByUsername(selectUser.getUsername());
        return selectUser;
    }

    //스팀 news 가져오기 test
    @GetMapping("/dummy/steam")
    public String test(Model model)
    {
        try {
            URL url = new URL("https://api.steampowered.com/ISteamNews/GetNewsForApp/v2/?AppID=1090630");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
            String result = bufferedReader.readLine();

            JSONParser jsonParser = new JSONParser();
            JSONObject json = (JSONObject)jsonParser.parse(result);
            JSONObject appNews = (JSONObject)json.get("appnews");
            JSONArray newsItems = (JSONArray)appNews.get("newsitems");
            System.out.println(appNews.get("appid"));

            model.addAttribute("appid", appNews.get("appid"));

//            List<String> set = new ArrayList<String>();
//            for (int i = 0 ; i < newsItems.size() ; i++) {
//                JSONObject newsItem = (JSONObject)newsItems.get(i);
//                System.out.println(newsItem.get("title"));
//                set.add(newsItem.get("title").toString());
//            }
//            model.addAttribute("title", set);

            model.addAttribute("newsItems", newsItems);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "steamTest";
    }
}
