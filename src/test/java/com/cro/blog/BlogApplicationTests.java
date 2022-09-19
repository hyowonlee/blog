package com.cro.blog;

import com.cro.blog.model.Reply;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogApplicationTests {

	@Test
	void contextLoads() {

	}

	@Test
    public void test(){
        Reply reply = new Reply().builder()
                .id(1)
                .user(null)
                .board(null)
                .content("hello")
                .build();

        System.out.println(reply);
    }

}
