package com.cro.blog.repository;

import com.cro.blog.model.Reply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

//    @Modifying //@Query 어노테이션 사용시 insert, update, delete 쿼리에서 사용되는 어노테이션
//    @Query(value="INSERT INTO reply(boardId, content, userId, createDate, updateDate) VALUES(?1, ?2, ?3, now(), now())", nativeQuery=true)
//    int nativeSave(int boardId, String content, int userId);
}
