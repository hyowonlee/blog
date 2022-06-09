package com.cro.blog.repository;

import com.cro.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// 이 리포지토리는 DAO라고 보면 됨
// @Repository //JpaRepository로 빈등록이 되기에 @Repository생략가능
public interface UserRepository extends JpaRepository<User, Integer> //<User테이블을 관리하는 리포지토리, User의 PK는 INT값>
{

}
