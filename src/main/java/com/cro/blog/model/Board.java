package com.cro.blog.model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Board {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략, 연결된 db의 넘버링 전략을 따라간다(mysql이니 auto-increment), application.yml에서 jpa의 기본 넘버링 전략을 따라가지 않게 false로 해놓고 db따라가게 적용해놓은것
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 저장용 어노테이션
    private String content; // 섬머노트 라이브러리 사용할거라 글 내용에 html태그가 섞여서 디자인됨 그래서 길이가 굉장히 길어질것

    @ColumnDefault("0") // 조회수 기본 0
    private int count; // 조회수

    @ManyToOne // Many = board, One = User
    @JoinColumn(name = "userId") // DB에는 userId라는 컬럼명으로 들어가게 될것
    private User user; // 작성자 정보 (JPA 사용시 오브젝트를 DB에 저장할 수 있게됨, ORM에서는 ID를 통한 외래키로 조인하지 않고 그냥 엔티티 객체 자체를 멤버변수로 넣어줌)

    @CreationTimestamp // 생성시 자동으로 시간 입력
    private Timestamp createDate; // 생성(가입) 날짜

    @UpdateTimestamp // Update시 자동으로 시간 입력
    private Timestamp updateDate; // 수정 날짜
}
