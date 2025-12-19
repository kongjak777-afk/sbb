package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/answer")                    // URL 프리픽스 앞에 /question 접두사를 지정
@RequiredArgsConstructor                          // final 필드를 생성자로 자동 주입(Lombok)
@Controller
public class AnswerController {

    //    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AnswerService answerService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable("id") Integer id,
                               @RequestParam(value = "content") String content) {

        Question question = this.questionService.getQuestions(id);

        answerService.createAnswer(question, content);            //   답변을 저장하기 위해 서비스를 호출
        return String.format("redirect:/question/detail/%s",id);

    }


}
