package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class QuestionController {

    private final QuestionService questionService;
//    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
//    @ResponseBody
    public String list(Model model) {

//        List<Question> questionList = questionRepository.findAll();
        List<Question> questionList = this.questionService.getlist();
        model.addAttribute("questionList",questionList);
        return "question_list";
    }
}
