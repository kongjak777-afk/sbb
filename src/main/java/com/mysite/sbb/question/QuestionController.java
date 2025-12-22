package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/question")                    // URL 프리픽스 앞에 /question 접두사를 지정
@RequiredArgsConstructor                          // final 필드를 생성자로 자동 주입(Lombok)
@Controller                                       // 스프링 MVC 컨트롤러 선언
public class QuestionController {

    private final QuestionService questionService; // 질문 관련 비즈니스 로직 처리 서비스

//    private final QuestionRepository questionRepository; // DB 직접 접근 레포지토리(현재 미사용)

    @GetMapping("/list")                  // /question/list GET 요청 매핑
//    @ResponseBody                                // JSON/String 그대로 응답(템플릿 미사용 시)
    public String list(Model model) {               // 질문 목록 요청 처리 메서드 스프링MVC에서 뷰로 데이터를 전달하기 위한 객체
        //  매개변수로 그냥 써도 자동주입 됨.

//        List<Question> questionList = questionRepository.findAll(); // 레포지토리 직접 조회
        List<Question> questionList = this.questionService.getlist(); // 서비스 통해 질문 목록 조회

        model.addAttribute("questionList", questionList); // 뷰로 전달할 데이터 저장

        return "question_list";                     // question_list.html 템플릿 렌더링
    }


    @GetMapping("/detail/{id}")   // id 가 뭐가 들어올지 모를때 자바에선 중괄호로 함

    public String detail(Model model,
                         @PathVariable Integer id,
                         AnswerForm answerForm) {               // 아이디에 대한 것을 화면으로부터 받도록 한다.

        Question question = this.questionService.getQuestions(id);
        model.addAttribute("question", question);
        return "question_detail";
    }


    @GetMapping("/create")
    public String qusetionCreate(QuestionForm questionForm) {   //양방향으로 화면컨트롤 (바인딩)

        return "question_form";
    }


    @PostMapping("/create")
    public String qusetionCreate(@Valid QuestionForm questionForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {        //
            return "question_form";
        }
        this.questionService.create(questionForm.getSubject(),questionForm.getContent());
        return "redirect:/question/list";

    }


}

