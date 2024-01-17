package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//spring data jpa의 기본기능을 쓰기 위해서는 Jparepository를 상송 해야함
//상속시에 entity명과 해당 entity의 pk타입을 명시
//구현 클래스와 스펙은 simpleJpaRepository class에 있고
//실질적인 구동상황에서 hibernate 구현체에 동작을 위임한다
public interface SpringDataJpaMemberRepository extends MemberRepository, JpaRepository<Member, Integer> {

}
