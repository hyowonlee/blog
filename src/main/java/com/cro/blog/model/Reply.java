package com.cro.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 이 어노테이션으로 jpa가 이 코드 읽어서 db에 테이블 생성, 이 클래스는 ORM(db에 매핑을 시켜준다)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne // 연관관계의 주인이란 FK를 가진 오브젝트(테이블)라고 볼 수 있는데 여기에서 FK 만들어줬으니
    // Board class와 Reply class에서 주인은 Reply class가 된다 그리고 Board class에서는 FK를 생성하지 않고 mappedBy로 Reply가 주인이라고 지정해 줬음
    @JoinColumn(name = "boardId") // 현재 Board Table에서는 Reply와의 FK Column을 만들지 않고 mappedBy로 주인을 지정했고 여기서 FK 만들어줬으니 연관관계의 주인은 Reply가 됨
    private Board board;

    @CreationTimestamp
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;
}
