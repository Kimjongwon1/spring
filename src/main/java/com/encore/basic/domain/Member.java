package com.encore.basic.domain;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//보통 Getter만 사용
@Getter
 //모든 매개변수를 넣은 생성자 (기본 생성자 없어짐)
//@NoArgsConstructor -> 기본 생성자 만들어줌
@Entity
@NoArgsConstructor
//entity어노테이션을 통해 mariadb의 테이블 및 컬럼을 자동생성
//class명을 테이블명으로, 변수명을 컬럼명으로
public class Member {
    @Setter
    @Id //pk설정
//    IDENTITY = auto increment 설정, auto = jpa구현체가 자동으로 적절한 키 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //autoincrement
    private int id;
//    String은 DB 들어갈 때 varchar로 변환됨
//    @Column
    private String name;
    @Column(nullable = false, length = 50) //name 옵션을 통해 DB에는 컬럼명 별도 지정
    private String email;
    private String password;
    @Setter
    @Column(name = "created_time") //name옵션을 통해 DB의 컬럼명 별도 지정
    @CreationTimestamp
    private LocalDateTime created_time;
   @UpdateTimestamp
   private LocalDateTime updatedTime;

    public Member(String name,String email,String password){
        this.name = name;
        this.email = email;
        this.password = password;
        this.created_time = LocalDateTime.now();
    }
    public void updateMember(String name, String password){
        this.name = name;
        this.password = password;
    }

}