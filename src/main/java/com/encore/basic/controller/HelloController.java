package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

// @RestController는 responsebody만, 즉 csr일 때 쓰면 됨.
@Controller
//class 차원에서 url경로를 지정하고 싶다면, @RequestMapping을 클래스 위에 선언하면서 경로지정
@RequestMapping("/hello")
public class HelloController {
//    @ResponseBody가 없고,
//    return타입이 String 이면 template 밑에 html 파일 확인
//    data만 리턴하는건 responsbody
    @GetMapping("/string")
    @ResponseBody
    public String helloSTring(){
        return "hello_String";
    }

    @GetMapping("/json")
    @ResponseBody
    public String helloJson(){
        return "hello_String";
    }
    @GetMapping("/screen")
    public String helloScreen(){
        return "screen";
    }

    @GetMapping("/screen-model")
//    ?name=hongildong의 방식으로 호출 (파라미터 호출 방식)
    public String helloScreenModel(Model model){
//        화면에 데이터를 넘기고 싶을 때는 Model객체 사용
        model.addAttribute("myData","honggildong");
        return "screen";
    }
    @GetMapping("/screen-model-param")
//    ?name=hongildong의 방식으로 호출 (파라미터 호출 방식)
    public String helloScreenModelParam(@RequestParam(value = "name")String name, Model model){
//        화면에 데이터를 넘기고 싶을 때는 Model객체 사용
        model.addAttribute("myData",name);
        return "screen";
    }

//    pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현 할 수 있어 좀 더 RestFul api 디자인에 적합함
    @GetMapping("/screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model){
        model.addAttribute("myData",id);
        return "screen";
    }
}

