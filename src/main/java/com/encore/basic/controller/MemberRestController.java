package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static com.encore.basic.controller.ResponseEntityController.*;

@RestController
@RequestMapping("/rest")
public class MemberRestController {

    private final MemberService memberService; //final을 붙여줘야함 -> 어차피 밑에 Constructor에서 초기화가 되기 때문에 괜찮음

    @Autowired
    public MemberRestController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members")
    public List<MemberResponseDto> showMembers() {
        return memberService.findAll();
    }

    @PostMapping("member/create")
    public String createMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.createMember(memberRequestDto);
        return "ok"; //url 리다이렉트
    }

//    @GetMapping("/member/find/{id}")
//    public MemberResponseDto findMember(@PathVariable int id) {
//        MemberResponseDto memberResponseDto = memberService.findById(id);
////         return memberService.findById(id); 강사님 답
//        return memberResponseDto;
//    }

//    @GetMapping("/member/find/{id}")
//    public ResponseEntity<MemberResponseDto> findMember(@PathVariable int id) {
//        MemberResponseDto memberResponseDto = null;
//
//        try {
//            memberResponseDto = memberService.findById(id);
//            return new ResponseEntity<>(memberResponseDto, HttpStatus.OK);
////            ResponseEntity.ok(memberResponseDto);
////            메소드 체이닝 방식
//        }
//        catch (EntityNotFoundException e){
//            e.printStackTrace();
//            return new ResponseEntity<>(memberResponseDto, HttpStatus.NOT_FOUND);
//        }
//
//    }
    @GetMapping("/member/find/{id}")
    public ResponseEntity<?> findMember(@PathVariable int id) {
        try {
        MemberResponseDto memberResponseDto = memberService.findById(id);
        // 성공 시 customMap2 메소드를 사용하여 응답 반환
        return ResponseEntityController.responsemessage(HttpStatus.OK, memberResponseDto);
     } catch (EntityNotFoundException e) {
        e.printStackTrace();
        // 실패 시 customMap1 메소드를 사용하여 응답 반환
        return ResponseEntityController.erroresponsemessage(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

//    @GetMapping("/member/delete/{id}")
//    public String delete(@PathVariable int id){
//        memberService.delete(id);
//        return "ok";
//    }
    @DeleteMapping("/member/delete/{id}")
    public String delete(@PathVariable int id){
        memberService.delete(id);
        return "ok";
    }

    @PatchMapping("member/update")
    public MemberResponseDto updateMember(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.updateMember(memberRequestDto);
        return memberService.findById(memberRequestDto.getId()); //url 리다이렉트
    }


}