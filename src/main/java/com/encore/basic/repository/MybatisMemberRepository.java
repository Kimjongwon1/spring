package com.encore.basic.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
//mybaits 레파지토리로 쓰겟다는 어노테이션

@Repository
@Mapper
public interface MybatisMemberRepository extends MemberRepository {
// 본문에 MybatisRepository에서 사용할 메소드 명세를 정의해야한다.
//    MemberRepository 에서 상속 받고 있으므로, 생략가능
//    실질적인 쿼리등 구현은 resources/mapper/MemberMapper.xml 파일에 수정
}
