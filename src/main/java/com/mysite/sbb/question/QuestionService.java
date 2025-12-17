package com.mysite.sbb.question;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QuestionService {
    // 스프링이 객체를 자동주입
    private final QuestionRepository questionRepository;

    public List<Question> getlist() {

        return this.questionRepository.findAll();       //겟리스트 메소드가 호출되면 퀘스쳔에 있는 모든 리스트를 가져온다.
    }

}
