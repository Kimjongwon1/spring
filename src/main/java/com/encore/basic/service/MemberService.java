package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.NoSuchElementException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//service 어노테이션을 통해 싱글톤 컴포넌트로 생성 -> Spring Bean으로 등록
@Service
//Spring Bean이란 스프링이 생성하고 관리하는 객체를 의미
//제어의 역전 (Inversion of Control) -> IOC 컨테이너가 Spring Bean을 관리 (빈 생성, 의존성 주입)
public class MemberService {
//    @Autowired // automatic dependency injection


//    private final MemberRepository memberRepository;
//private final MemberRepository memberRepository;
    private final MemberRepository memberRepository;


    @Autowired
    public MemberService(MybatisMemberRepository springDataJpaMemberRepository) {
        this.memberRepository = springDataJpaMemberRepository;
    }



    public List<MemberResponseDto> findAll() {
        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for (Member m : members) {
            MemberResponseDto memberResponseDto = new MemberResponseDto(m.getId(), m.getName(), m.getEmail(), m.getPassword(), m.getCreated_time());
//            memberResponseDto.setName(m.getName());
//            memberResponseDto.setEmail(m.getEmail());
//            memberResponseDto.setPassword(m.getPassword());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    public void createMember(MemberRequestDto memberRequestDto) {

        Member member = new Member(memberRequestDto.getName(),
                memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }

//    public void createMember(MemberRequestDto memberRequestDto) {
//        LocalDateTime now = LocalDateTime.now();
//        total_id++;
//        Member member = new Member(total_id, memberRequestDto.getName(),
//                memberRequestDto.getEmail(), memberRequestDto.getPassword(), now);
//        memberRepository.save(member);
//    }

//    public MemberResponseDto findById(int id) throws NoSuchElementException
// /*Member = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);*/
// /*Member = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);*/
public MemberResponseDto findById(int id) {
    return memberRepository.findById(id)
            .map(member -> new MemberResponseDto(
                    member.getId(),
                    member.getName(),
                    member.getEmail(),
                    member.getPassword(),
                    member.getCreated_time()))
            .orElseThrow(NoSuchElementException::new); // Optional이 비었을 때 NoSuchElementException을 발생시킴
}
}