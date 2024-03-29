package com.cro.blog.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity // 이 어노테이션으로 jpa가 이 코드 읽어서 db에 테이블 생성, 이 클래스는 ORM(db에 매핑을 시켜준다)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Board {

    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략, 연결된 db의 넘버링 전략을 따라간다(mysql이니 auto-increment), application.yml에서 jpa의 기본 넘버링 전략을 따라가지 않게 false로 해놓고 db따라가게 적용해놓은것
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터 저장용 어노테이션
    private String content; // 섬머노트 라이브러리 사용할거라 글 내용에 html태그가 섞여서 디자인됨 그래서 길이가 굉장히 길어질것

    private int count; // 조회수

    @ManyToOne(fetch = FetchType.EAGER) // 연관관계 세팅, Many = board, One = User, 이놈은 many to one으로 1건밖에 없으니 무조건 가져와도 됨 그래서 eager 전략 세팅 (manytoone의 기본전략은 eager fetch 안써주면 eager)
    @JoinColumn(name = "userId") // DB에는 userId라는 컬럼명으로 들어가게 될것 (여기선 객체지만 db에 만들어질땐 user의 int id값인 FK로 데이터가 들어갈것)
    private User user; // 작성자 정보 (JPA 사용시 오브젝트를 DB에 저장할 수 있게됨, ORM에서는 ID를 통한 외래키로 조인하지 않고 그냥 엔티티 객체 자체를 멤버변수로 넣어줌)

    @OneToMany(mappedBy = "board", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE) // 이놈은 mappedBy 되어있어서 테이블에 들어가는 놈이 아님 나중에 select 하기위해서 연관관계 세팅해준것 (cascade 옵션으로 board 삭제시 댓글도 같이 ㅅ삭제)
   //mappedBy를 세팅하면 이 클래스가 FK를 가진 연관관계의 주인이 아님 즉 board와 reply의 FK컬럼은 reply테이블에 만들것
    //그렇다면 이 onetomany는 board를 select시 join을 통해 값을 얻어오기 위해 필요한 것, mappedBy = "board"는 Reply 클래스에 있는 board 필드를 의미하는 것 (mappedBy를 쓰면 이 멤버는 연관관계의 주인이 아니라는거 이건 Reply.java에서 설명)
    //reply는 one to many라 여러건이 있을수도 있음 그래서 필요할때만 들고오게 lazy 전략이 기본임(onetomany의 기본전략은 lazy fetch 안써주면 lazy)
    //하지만 우리는 ui를 항상 댓글이 보이게 하려고 하니 eager전략으로 바꾼다다    //@JoinColumn(name = "replyId") 이건 안씀 // 이 답글은 Reply 클래스에서 Board를 FK로 가지고 가기에 이 테이블에 FK column을 만들 필요가 없음 (여기에 fk가 만들어지면 여러개가 들어갈수있으니 원자성이 위배됨)
    //만약 여기서 FK를 만들면 reply가 여러개인경우 reply column에 값이 하나가 아닐수있으니 원자성이 위배됨 그래서 여기서 만들면 안됨
    @JsonIgnoreProperties({"board"}) // 무한참조 방지 어노테이션으로 jackson라이브러리를 통해 json으로 리턴할때 getter를 사용해서 값을 가져오기에 reply 안에 있는 board를 다시 요청해서 무한참조가 됨 그걸 방지하려고 reply안에있는 board를 다시 호출하지 말라는 의미(json으로 바꾸지 않겠다)의 어노테이션
    // board를 통해 reply를 요청할때 reply 안에 있는 board는 다시 요청하지 않겠다(json 리턴시 데이터를 안보내겠다)라는 의미
    @OrderBy("id desc") // 내림차순 정렬
    private List<Reply> replys; // @OneToMany는 이 클래스에 여러개가 들어올 수 있으니 List로 세팅

    @CreationTimestamp // 생성시 자동으로 시간 입력
    private Timestamp createDate; // 생성(가입) 날짜

    @UpdateTimestamp // Update시 자동으로 시간 입력
    private Timestamp updateDate; // 수정 날짜
}
