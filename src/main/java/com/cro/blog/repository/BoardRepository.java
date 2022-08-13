package com.cro.blog.repository;

import com.cro.blog.model.Board;
import com.cro.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 이 리포지토리는 DAO라고 보면 됨
// @Repository //JpaRepository로 빈등록이 되기에 @Repository생략가능
public interface BoardRepository extends JpaRepository<Board, Integer> //<User테이블을 관리하는 리포지토리, User의 PK는 INT값>
{
    //JPA Naming 전략 : JPA는 함수 이름을 쿼리문을 만들어서 db에 접근하는데 그 규칙이 jpa naming 전략

    //DB에 INSERT하는 save 함수는 이미 만들어져 있음 따로 안만들어도 됨

}
