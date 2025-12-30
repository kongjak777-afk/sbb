package com.mysite.sbb;

import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SbbApplicationTests {

    @Autowired                                          //의존성 주입을 자동화
    private QuestionRepository questionRepository;  //객체를 생성하지 않고 DI(의존성 주입) 이 되어서 생성자를 만들지 않고 사용
    @Autowired                                          //의존성 주입을 자동화
    private QuestionService questionService;
//    questionRepository = new QuestionRepository();


//    @Autowired
//    private AnswerRepository answerRepository;


//    @Test
////    void contextLoads() {
////    }
//    void testJpa() {
//        Question q1 = new Question();
//        q1.setSubject("sbb가 무엇인가?");
//        q1.setContent("SBB에 대해서 알고싶습니다.");
//        q1.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q1);
//
//        Question q2 = new Question();
//        q2.setSubject("스프링부트 모델 질문");
//        q2.setContent("id 는 자동으로 생성되나요?");
//        q2.setCreateDate(LocalDateTime.now());
//        this.questionRepository.save(q2);


//        Answer a1 = new Answer();
//        a1.setId(1);
//        a1.setQuestion(q1);
//        a1.setContent("test");
//        a1.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a1);
//    }


    @Test
    void makeTestData() {
        for (int i = 1; i <= 300; i++) {
            String subject = String.format("테스트 데이터입니다.:[%03d]", i);
            String content = "내용 없음";
            this.questionService.create(subject, content, null);
        }
    }
}
