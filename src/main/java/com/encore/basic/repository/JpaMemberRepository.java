package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Repository
public class JpaMemberRepository implements MemberRepository {
    private final EntityManager entityManager;
//Entitymanager는 jpa의 핵심클래스(객체)
//Entity의 생명주기를 관리, 데이터베이스와의 상호작용을 한다.
//Entity를 대상으로 crud 기능을 제공
    public JpaMemberRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = entityManager.createQuery("select m from Member m",Member.class).getResultList();
//                jpql:jpa의 쿼리문법
//                장점: DB에 따라 문법이 달라지지 않는 객체지향 언어, 컴파일타임에서 check, (SpringDatajpa의 @Query의 기능)
//                단점: DB고유의 기능과 성능을 극대화하기는 어려움
        return members;
    }

    @Override
    public Member save(Member member) {
//        persist: 전달된 member(entityManager)가 관리 상태가 되도록 만들어주고,
//        트랜잭션이 커밋될때 데이터베이스에 저장한다. insert포함
        entityManager.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
//        find 메소드는 pk를 매개변수로 둔다.
        Member member = entityManager.find(Member.class, id);

        return Optional.ofNullable(member);
    }

    @Override
    public void delete(Member member) {
//        remove
        entityManager.remove(member);
    }


//    @Override
//    public void update(Member member){
////        merge
//        //entityManager.merge(member);
//    }

//        pk 외 컬럼으로 조회할때는
//    public List<Member> findByName(String name) {
////        :name 은 그 자리에 ?를 넣는것과 같다
//        List<Member> members =entityManager.createQuery("select m from Member m where m.name = :name", Member.class)
//                .setParameter("name",name).getResultList();
////        그 외 컬럼으로 조회할때는
//        return members;
//    }
}
