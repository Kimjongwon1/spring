package com.encore.basic.controller;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MemberController {
    @GetMapping("/members")
    public String helloScreen() {
        return "member-create";
    }
}
//회원가입,회원목록조회
