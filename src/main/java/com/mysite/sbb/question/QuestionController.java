package com.mysite.sbb.question;

import com.mysite.sbb.answer.AnswerForm;
import com.mysite.sbb.user.SiteUser;
import com.mysite.sbb.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;

@RequestMapping("/question")                    // URL 프리픽스 앞에 /question 접두사를 지정
@RequiredArgsConstructor                          // final 필드를 생성자로 자동 주입(Lombok)
@Controller                                       // 스프링 MVC 컨트롤러 선언
public class QuestionController {

    private final QuestionService questionService; // 질문 관련 비즈니스 로직 처리 서비스
    private final UserService userService;

//    private final QuestionRepository questionRepository; // DB 직접 접근 레포지토리(현재 미사용)

//    @GetMapping("/list")                  // /question/list GET 요청 매핑
////    @ResponseBody                                // JSON/String 그대로 응답(템플릿 미사용 시)
//    public String list(Model model) {               // 질문 목록 요청 처리 메서드 스프링MVC에서 뷰로 데이터를 전달하기 위한 객체
//        //  매개변수로 그냥 써도 자동주입 됨.
//

    /// /        List<Question> questionList = questionRepository.findAll(); // 레포지토리 직접 조회
//        List<Question> questionList = this.questionService.getList(); // 서비스 통해 질문 목록 조회
//
//        model.addAttribute("questionList", questionList); // 뷰로 전달할 데이터 저장
//
//        return "question_list";                     // question_list.html 템플릿 렌더링
//    }
    @GetMapping("/list")
    public String list(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
        Page<Question> paging = this.questionService.getList(page);
        model.addAttribute("paging", paging);
        return "question_list";
    }


    @GetMapping("/detail/{id}")   // id 가 뭐가 들어올지 모를때 자바에선 중괄호로 함

    public String detail(Model model,
                         @PathVariable Integer id,
                         AnswerForm answerForm) {               // 아이디에 대한 것을 화면으로부터 받도록 한다.

        Question question = this.questionService.getQuestion(id);
        model.addAttribute("question", question);
        return "question_detail";
    }

    @PreAuthorize("isAuthenticated()")            //  현재 사용자가 **로그인(인증)된 상태인지** 확인하는 표현식
    @GetMapping("/create")
    public String qusetionCreate(QuestionForm questionForm) {   //양방향으로 화면컨트롤 (바인딩)

        return "question_form";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String qusetionCreate(@Valid QuestionForm questionForm,
                                 BindingResult bindingResult,
                                 Principal principal) {
        if (bindingResult.hasErrors()) {        //
            return "question_form";
        }
        SiteUser siteUser = userService.getUser(principal.getName());
        this.questionService.create(questionForm.getSubject(), questionForm.getContent(), siteUser);
        return "redirect:/question/list";

    }




    @PreAuthorize("isAuthenticated()")              //  현재 사용자가 **로그인(인증)된 상태인지** 확인하는 표현식
    @GetMapping("/modify/{id}")                   // <a 앵커 태그로 들어온 메서드 처리
    public String  questionModify(QuestionForm questionForm,
                                  @PathVariable Integer id,
                                  Principal principal) {
        Question question = this.questionService.getQuestion(id);
        if(!question.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }
        questionForm.setSubject(question.getSubject());
        questionForm.setContent(question.getContent());
        return "question_form";
    }


    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify/{id}")                //post 방식으로 들어온 메서드 처리
    public String qusetionModify(@Valid QuestionForm questionForm,      // 메서드 오버로딩 위와 같은 메서드명이지만 파라미터 갯수가 다름
                                 BindingResult bindingResult,
                                 Principal principal,
                                 @PathVariable Integer id) {
        if (bindingResult.hasErrors()) {        //
            return "question_form";
        }
        Question question = this.questionService.getQuestion(id);

        if(!question.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"수정권한이 없습니다.");
        }

        this.questionService.modify(question, questionForm.getSubject(), questionForm.getContent());
        return String.format("redirect:/question/detail/%d", id);

    }


    @PreAuthorize("isAuthenticated()")              //  현재 사용자가 **로그인(인증)된 상태인지** 확인하는 표현식
    @GetMapping("/delete/{id}")                   // <a 앵커 태그로 들어온 메서드 처리
    public String  questionDelete(@PathVariable Integer id,  // URL 경로에 포함된 값(/delete/{id})을 받아오는 질문 번호
                                  Principal principal) {  // 현재 로그인한 사용자의 인증 정보 객체 userName
        Question question = this.questionService.getQuestion(id);

        if(!question.getAuthor().getUserName().equals(principal.getName())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"삭제권한이 없습니다.");
        }
        this.questionService.delete(question);
        return "redirect:/";
    }


}

