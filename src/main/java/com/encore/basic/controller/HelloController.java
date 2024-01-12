package com.encore.basic.controller;


import com.encore.basic.domain.HelloTest;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// @RestController는 responsebody만, 즉 csr일 때 쓰면 됨.
@Controller
//class 차원에서 url경로를 지정하고 싶다면, @RequestMapping을 클래스 위에 선언하면서 경로지정
@RequestMapping("/hello")
public class HelloController {
//    @ResponseBody가 없고,
//    return타입이 String 이면 template 밑에 html 파일 확인
//    data만 리턴하는건 responsbody

    //getmapping과 같음
//        @RequestMapping(value = "string", method = RequestMethod.GET)
//        @ResponseBody
//        public String RequestMappingSTring(){
//    return "hello_String";
//}
    @GetMapping("/string")
    @ResponseBody
    public String helloSTring() {
        return "hello_String";
    }

    //    json parsing은 내부적으로 기동하는 objectMapper가 기본적으로json을 처리해준다
    @GetMapping("/json")
    @ResponseBody
    public HelloTest helloJson() {
        HelloTest hello = new HelloTest();
        hello.setPassword("qlalfqjsgh");
        hello.setName("honggil");
        hello.setEmail("main@mail.com");
        System.out.println(hello);

        return hello;
    }

    @GetMapping("/screen")
    public String helloScreen() {
        return "screen";
    }

    @GetMapping("/screen-model")
//    ?name=hongildong의 방식으로 호출 (파라미터 호출 방식)
    public String helloScreenModel(Model model) {
//        화면에 데이터를 넘기고 싶을 때는 Model객체 사용
        model.addAttribute("myData", "honggildong");
        return "screen";
    }

    @GetMapping("/screen-model-param")
//    ?name=hongildong의 방식으로 호출 (파라미터 호출 방식)
    public String helloScreenModelParam(@RequestParam(value = "name") String name, Model model) {
//        화면에 데이터를 넘기고 싶을 때는 Model객체 사용
        model.addAttribute("myData", name);
        return "screen";
    }

    //    pathvariable 방식은 url을 통해 자원의 구조를 명확하게 표현 할 수 있어 좀 더 RestFul api 디자인에 적합함
    @GetMapping("/screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model) {
        model.addAttribute("myData", id);
        return "screen";
    }

    // Form태그로 x-www 데이터 처리
    @GetMapping("/form-screen")
    public String formScreen() {
        return "htmlNew";
    }

    ;

    @PostMapping("form-post-handle")
//    form태그를 통한 body의 데이터 형태가 key1=value1 &key2=value2;
    @ResponseBody
    public String formPostHandle(@RequestParam(value = "name") String input1,
                                 @RequestParam(value = "email") String input2,
                                 @RequestParam(value = "password") String input3) {
        System.out.println("Name : " + input1);
        System.out.println("Email : " + input2);
        System.out.println("Password : " + input3);
        return "정상처리";
    }
    @PostMapping("form-post-handle2")
//    Spring에서 Hello클래스의 인스턴스를 자동 매핑하여 생성
//    form-data 형식 즉, x-www-url인코딩 형식의 경우 사용
//    HelloTest 클래스를 통해 데이터 바인딩을 한다.(클래스에 setter 필수)
    @ResponseBody
    public String formPostHandle2(HelloTest helloTest) {
        System.out.println(helloTest);
        return "정상처리";
    }
    //json데이터 처리
    @GetMapping("json-screen")
    public String jsonScreen() {
        return "hello-json-screen";
    }

    @PostMapping("json-post-handle1")
    @ResponseBody
//    @RequestBody는 json으로 post요청이 들어왔을때 body에서 data를 꺼내기위해 사용
    public String jsonposthandle(@RequestBody Map<String,String> body) {
        System.out.println(body.get("name"));
        System.out.println(body.get("email"));
        System.out.println(body.get("password"));
        HelloTest helloTest = new HelloTest();
        helloTest.setName(body.get("name"));
        helloTest.setName(body.get("email"));
        helloTest.setName(body.get("password"));
        return "ok";
    }

    @PostMapping("json-post-handle2")
    @ResponseBody
    public String jsonposthandle2(@RequestBody JsonNode body) {
//        System.out.println("이름 : " + body.get("name").asText());
//        System.out.println("이멜 : " + body.get("email").asText());
//        System.out.println("비번 : " + body.get("password").asText());
        HelloTest helloTest = new HelloTest();
        helloTest.setName(body.get("name").asText());
        helloTest.setName(body.get("email").asText());
        helloTest.setName(body.get("password").asText());
//        List<HelloTest> myList = new ArrayList<>();

        return "ok";
    }
    @PostMapping("json-post-handle3")
    @ResponseBody
    public String jsonposthandle3(@RequestBody HelloTest helloTest) {
        System.out.println(helloTest);
        return "ok";
    }
}


