package com.mysite.sbb;                      // 패키지 경로: 프로젝트 내 위치를 나타냄

import org.springframework.stereotype.Controller;   // 스프링 MVC에서 컨트롤러임을 알려주는 어노테이션
import org.springframework.web.bind.annotation.GetMapping;  // GET 요청 URL 매핑용 어노테이션
import org.springframework.web.bind.annotation.ResponseBody; // 메서드 반환값을 그대로 HTTP 응답 본문으로 보냄

@Controller                                      // 이 클래스는 웹 요청을 처리하는 컨트롤러임을 스프링에 알림
public class HelloController {

    @GetMapping("/hello")                         // 브라우저에서 /hello 로 GET 요청이 오면 이 메서드(메서드 이름은 상관X)를 실행
//    @ResponseBody                                  // HTTP 를 통해 return 값 "Hello World!" 를  출력  (다른 url 로 가고 싶으면 이 문구 제거)
    public String hello() {
//        return "Hello World!";                    // 브라우저에 Hello World! 를 직접 출력
        return "hello";                    // 브라우저에 Hello World! 를 직접 출력
    }


    @GetMapping("/hi")
//    @ResponseBody                                  // HTTP 를 통해 return 값 "Hello World!" 를  출력  (다른 url 로 가고 싶으면 이 문구 제거)
    public String hi() {
//        return "Hello World!";
        return "hi";
    }
}
