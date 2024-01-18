package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("response/entity")
public class ResponseEntityController {
//    @ResponseStatus 어노테이션 방식
    @GetMapping("responsesstatus")
    @ResponseStatus(HttpStatus.CREATED)
    public String responseStatus(){
        return "OK";
    }
    @GetMapping("responsesstatus2")
    @ResponseStatus(HttpStatus.CREATED)
    public Member responseStatus2(){
        Member member = new Member("a1223","asd@anv.cer","abcde");
        return member; //201이 리턴되게
    }
    
//    responseentity 객체를 직접 생성
    @GetMapping("custom1")
    public ResponseEntity<Member> custom1(){
        Member member = new Member("a1223","asd@anv.cer","abcde");
        return new ResponseEntity<>(member,HttpStatus.CREATED); //201이 리턴되게
    }
    
//    responseEntity가 <String>일 경우 text/html로 설정됨
    @GetMapping("custom2")
    public ResponseEntity<String> custom2(){
        String html = "<h1>없는 아이디 입니다.</h1>";
        return new ResponseEntity<>(html,HttpStatus.NOT_FOUND); //201이 리턴되게
    }
    //  map형태의 메시지 커스텀
//    실패하면
//    @GetMapping("/map_custom1")
//    public static ResponseEntity<Map<String,Object>> erroresponsemessage(MemberResponseDto memberResponseDto){
//        Map<String, Object> body = new HashMap<>();
//        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        body.put("status",Integer.toString(httpStatus.value()));
//        body.put("error message",httpStatus.getReasonPhrase());
//        return new ResponseEntity<>(body,httpStatus);
//    }

    @GetMapping("/map_custom1")
    public static ResponseEntity<Map<String,Object>> erroresponsemessage(HttpStatus status, String message){
        Map<String, Object> body = new HashMap<>();
        body.put("status",Integer.toString(status.value()));
        body.put("error message",message);
        return new ResponseEntity<>(body,status);
    }

    //    성공하면
//    @GetMapping("/map_custom2")
//    public static ResponseEntity<Map<String, Object>> responsemessage() {
//        Map<String, Object> body = new HashMap<>();
//        HttpStatus httpStatus = HttpStatus.CREATED;
//        body.put("status", httpStatus.value());
//        Member member = new Member("a1223","asd@anv.cer","abcde");
//        body.put("message", member);
//        return new ResponseEntity<>(body, httpStatus);
//    }
    @GetMapping("/map_custom2")
    public static ResponseEntity<Map<String, Object>> responsemessage(HttpStatus status, Object object) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", Integer.toString(status.value()));
        body.put("message", object);
        return new ResponseEntity<>(body, status);
    }

//    status 201, message : 객체

//    메소드 체이닝 : Response Entity에 클래스 메소드 사용
    @GetMapping("chaining1")
    public ResponseEntity<Member> chaining1(){
        Member member = new Member("a1223","asd@anv.cer","abcde");
        return ResponseEntity.ok(member);
    }
    @GetMapping("chaining2")
    public ResponseEntity<String> chaining2(){
        return ResponseEntity.notFound().build();
    }
    @GetMapping("chaining3")
    public ResponseEntity<Member> chaining3(){
        Member member = new Member("a1223","asd@anv.cer","abcde");
        return ResponseEntity.status(HttpStatus.CREATED).body(member);
    }
}

