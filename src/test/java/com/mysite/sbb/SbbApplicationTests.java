package com.mysite.sbb;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class SbbApplicationTests {

    @Autowired                                          //의존선 주입을 자동화
    private QuestionRepository questionRepository;  //객체를 생성하지 않고 DI(의존성 주입) 이 되어서 생성자를 만들지 않고 사용
//    questionRepository = new QuestionRepository();


//    @Autowired
//    private AnswerRepository answerRepository;


    @Test
//    void contextLoads() {
//    }
    void testJpa() {
        Question q1 = new Question();
        q1.setSubject("sbb가 무엇인가?");
        q1.setContent("SBB에 대해서 알고싶습니다.");
        q1.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q1);

        Question q2 = new Question();
        q2.setSubject("스프링부트 모델 질문");
        q2.setContent("id 는 자동으로 생성되나요?");
        q2.setCreateDate(LocalDateTime.now());
        this.questionRepository.save(q2);


//        Answer a1 = new Answer();
//        a1.setId(1);
//        a1.setQuestion(q1);
//        a1.setContent("test");
//        a1.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a1);
    }

}
