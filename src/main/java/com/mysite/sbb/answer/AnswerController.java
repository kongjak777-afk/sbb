package com.mysite.sbb.answer;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionService;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequestMapping("/answer")                    // URL 프리픽스 앞에 /question 접두사를 지정
@RequiredArgsConstructor                          // final 필드를 생성자로 자동 주입(Lombok)
@Controller
public class AnswerController {

    //    private final AnswerService answerService;
    private final QuestionService questionService;
    private final AnswerService answerService;
    private final UserService userService;


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createAnswer(Model model,
                               @PathVariable("id") Integer id,
                               @Valid AnswerForm answerForm,
                               BindingResult bindingResult,
                               Principal principal) {   // 시큐리티 객체 로그인 사용자의 정보 가지고 있음


        Question question = this.questionService.getQuestions(id);
        SiteUser siteUser = userService.getUser(principal.getName());

        if (bindingResult.hasErrors()) {
            model.addAttribute("question", question);
            return "question_detail";
        }

        this.answerService.createAnswer(question, answerForm.getContent(), siteUser);            //   답변을 저장하기 위해 서비스를 호출
        return String.format("redirect:/question/detail/%s",id);




    }





}
