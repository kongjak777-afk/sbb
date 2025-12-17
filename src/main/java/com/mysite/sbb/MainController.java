package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller                                 //  메인콘트롤러가 컨트롤러 라고 어노테이션 작업
public class MainController {

    @GetMapping("sbb")
    @ResponseBody
    public String index() {

        return "안녕하세요! sbb에 오신것을 환영합니다.";
    }

    @GetMapping("/")                                    //  루트패스로 들어오면 아래의 메소드의 링크로 리다이렉트
    public String root() {

        return "redirect:/question/list";       //url 을 강제로 지정
    }

}
