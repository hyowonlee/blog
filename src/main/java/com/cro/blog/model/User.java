package com.cro.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity // 이 어노테이션으로 jpa가 이 코드 읽어서 db에 테이블 생성, 이 클래스는 ORM(db에 매핑을 시켜준다)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@DynamicInsert // insert시 null인 필드를 제외함
public class User {
    @Id // PK 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 넘버링 전략, 연결된 db의 넘버링 전략을 따라간다(mysql이니 auto-increment), application.yml에서 jpa의 기본 넘버링 전략을 따라가지 않게 false로 해놓고 db따라가게 적용해놓은것
    private int id; // PK, auto-increment로 넘버링 할것

    @Column(nullable = false, length = 30) // null이 되면 안되니 not null 설정, 길이 제한 설정
    private String username; // id

    @Column(nullable = false, length = 100) //해시화 되면 길이가 길어지니 길이 제한 길게함
    private String password; // pw

    @Column(nullable = false, length = 50) // null이 되면 안되니 not null 설정, 길이 제한 설정
    private String email; // email

    //@ColumnDefault("'user'") // 이 column의 디폴트값 세팅 문자 'user'라는걸 알리기 위해 "" 안에 ''넣어서 세팅해야됨,
    // 이 기본값설정은 db에서만 설정되어있으므로 insert시 null 값을 insert하면 기본값이 아닌 null이 들어가게됨 그래서 아예 insert문에서 필드를 날려서 null도 안들어가게 해야됨
    // 그때 필요한게 클래스 어노테이션에 있는 @DynamicInsert임 insert시 null인 필드를 제외함 (근데 그냥 구현함 어노테이션 많이쓰면 꼼수만 는데)
    @Enumerated(EnumType.STRING) // enum타입을 엔티티클래스의 속성으로 사용할 수 있게 해줌, db에는 RoleType이란게 없으니 이게 Enum의 이름(String)을 저장할거라고 설정 EnumType.ORDINAL는 enum의 순서 값을 의미함
    private RoleType role; // 내가 만든 Enum RoleType.java로 선언 db는 해당 타입을 인식할 수 없으니 인식해서 column을 만들 수 있게 어노테이션으로 세팅

    @CreationTimestamp // 시간이 자동 입력됨 (쿼리로 NOW() 쓰는거처럼 시간 자동입력) 이건 hibernate에서 제공하는 어노테이션
    private Timestamp createDate; // 생성(가입) 날짜

    @UpdateTimestamp // Update시 자동으로 시간 입력
    private Timestamp updateDate; // 회원정보 수정 날짜
}
