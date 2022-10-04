package com.cro.blog.service;

import com.cro.blog.dto.ReplySaveRequestDto;
import com.cro.blog.model.Board;
import com.cro.blog.model.Reply;
import com.cro.blog.model.User;
import com.cro.blog.repository.BoardRepository;
import com.cro.blog.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository; // lombok의 @RequiredArgsConstructor를 이용한 생성자 주입
    private final ReplyRepository replyRepository;

    // 글 저장
    // exception시 GlobalExceptionHandler.java에서 에러 잡아서 리턴될것
    @Transactional // 이 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
    public void save(Board board, User user)
    {
        board.setCount(0); // 조회수 세팅
        board.setUser(user);
        boardRepository.save(board); // db에 insert
    }

    // index 페이지 요청시 글 목록 가져오기
    @Transactional(readOnly = true)
    public Page<Board> boardList(Pageable pageable) // index 페이지 부르는 컨트롤러에서 매개변수로 페이징하는 Pageable 클래스를 받아오는데 그걸로 findAll에 넘겨주면 페이징 가능
    {
        return boardRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Board boardDetail(int id)
    {
        return boardRepository.findById(id)
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 상세보기 실패 : 아이디를 찾을 수 없습니다");
                });
    }

    @Transactional
    public void boardDelete(int id)
    {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void boardUpdate(int id, Board requestBoard)
    {
        Board board = boardRepository.findById(id) // 글 수정하기위해 일단 db에서 select해서 가져와서 영속화 시킴
                .orElseThrow(()->{
                    return new IllegalArgumentException("글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                });

        // 가져온 board 객체에서 수정해야 영속화된 객체를 수정하는거니 꼭 select해온 객체를 수정해야됨
        board.setTitle(requestBoard.getTitle());
        board.setContent(requestBoard.getContent());
        // 이 함수 종료시 트랜잭션이 종료되고 더티체킹 되어서 수정사항이 자동 업데이트 될것
    }

    // 댓글 저장
    @Transactional // 이 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
    public void replySave(ReplySaveRequestDto replySaveRequestDto, User user)
    {
        Board board = boardRepository.findById(replySaveRequestDto.getBoardId()) // 댓글에 들어갈 board 객체 찾기
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 쓰기 실패 : 게시글 아이디를 찾을 수 없습니다.");
                });

        Reply reply = Reply.builder()
                .user(user)
                .board(board)
                .content(replySaveRequestDto.getContent())
                .build();

        replyRepository.save(reply); // db에 insert
    }

    @Transactional
    public void replyDelete(int id)
    {
        replyRepository.deleteById(id);
    }

    @Transactional
    public void replyUpdate(Reply requestReply)
    {
        Reply reply = replyRepository.findById(requestReply.getId()) // 글 수정하기위해 일단 db에서 select해서 가져와서 영속화 시킴
                .orElseThrow(()->{
                    return new IllegalArgumentException("댓글 찾기 실패 : 아이디를 찾을 수 없습니다.");
                    });

        // 가져온 board 객체에서 수정해야 영속화된 객체를 수정하는거니 꼭 select해온 객체를 수정해야됨
        reply.setContent(requestReply.getContent());
        // 이 함수 종료시 트랜잭션이 종료되고 더티체킹 되어서 수정사항이 자동 업데이트 될것
    }

//    // native query 댓글 저장
//    @Transactional // 이 로직을 트랜잭션으로 등록, 성공시 db commit 실패시 rollback될것
//    public void replySave(ReplySaveRequestDto replySaveRequestDto, User user)
//    {
//        int result = replyRepository.nativeSave(replySaveRequestDto.getBoardId(), replySaveRequestDto.getContent(), user.getId());
//        System.out.println("insert : " + result);
//        System.out.println();
//    }
}
