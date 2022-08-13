package com.cro.blog.service;

import com.cro.blog.model.Board;
import com.cro.blog.model.User;
import com.cro.blog.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입

    // 글 저장
    // exception시 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것
    @Transactional // 이 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
    public void save(Board board, User user)
    {
        board.setCount(0); // 조회수 세팅
        board.setUser(user);
        boardRepository.save(board); // db에 insert
    }

}
