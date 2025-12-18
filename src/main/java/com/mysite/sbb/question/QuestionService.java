package com.mysite.sbb.question;

import com.mysite.sbb.DataNotFoundExeption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class QuestionService {
    // 스프링이 객체를 자동주입
    private final QuestionRepository questionRepository;

    public List<Question> getlist() {

        return this.questionRepository.findAll();       //겟리스트 메소드가 호출되면 퀘스쳔에 있는 모든 리스트를 가져온다.
    }


    public Question getQuestions(Integer id) {                // id(질문 번호)를 받아 질문 1개를 조회하는 메소드
        Optional<Question> question =                          // Question이 있을 수도 있고 없을 수도 있는 객체(Optional)
                questionRepository.findById(id);               // DB에서 해당 id의 질문을 조회

        if (question.isPresent()) {                             // 조회 결과가 존재한다면 (DB에 질문이 있다면)
            return question.get();                              // Optional 안에 들어있는 Question 객체를 꺼내서 반환
        } else {                                                // 조회 결과가 없다면 (해당 id의 질문이 없으면)
             return null;                                     // 아무 질문도 없다는 의미로 null 반환 (권장되지는 않음)
//            throw new DataNotFoundExeption("question not found");
        }
    }

}
