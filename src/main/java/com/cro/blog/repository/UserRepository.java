package com.cro.blog.repository;

import com.cro.blog.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 이 리포지토리는 DAO라고 보면 됨
// @Repository //JpaRepository로 빈등록이 되기에 @Repository생략가능
public interface UserRepository extends JpaRepository<User, Integer> //<User테이블을 관리하는 리포지토리, User의 PK는 INT값>
{
    Optional<User> findById(int id); // Optional 객체가 null 처리가 쉬워서 아무것도 못가져오는 null상태 처리를 위해 Optional로 반환함
    //사실 findById는 기본적으로도 만들어져있어서 안만들어도 쓸수있지만 헷갈리니 만듬

    Optional<User> findByUsername(String username);

    void deleteById(int id);
    //사실 deleteById 기본적으로도 만들어져있어서 안만들어도 쓸수있지만 헷갈리니 만듬

    void deleteByUsername(String username);

    boolean existsByUsername(String username); // 해당 데이터가 db에 존재하는지 확인하기 위해 existsBy를 사용할 수 있음 존재시 t 없을시 f
}
