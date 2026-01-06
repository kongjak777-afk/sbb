package com.mysite.sbb.answer;

import com.mysite.sbb.DataNotFoundException;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public void createAnswer (Question question, String content, SiteUser siteUser){
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(siteUser);
        this.answerRepository.save(answer);
    }


    public Answer getAnswer(Integer id) {                // id(질문 번호)를 받아 질문 1개를 조회하는 메소드
        Optional<Answer> answer =                          //  있을 수도 있고 없을 수도 있는 객체(Optional)
                answerRepository.findById(id);

        if (answer.isPresent()) {                             // 조회 결과가 존재한다면 (DB에 질문이 있다면)
            return answer.get();                              // Optional 안에 들어있는  객체를 꺼내서 반환
        } else {                                                // 조회 결과가 없다면 (해당 id의 질문이 없으면)
//             return null;                                     // 아무 질문도 없다는 의미로 null 반환 (권장되지는 않음)
            throw new DataNotFoundException("answer not found");  // throw 문법
        }
    }



    public void modify(Answer answer, String content) {

        answer.setContent(content);
        answer.setModifyDate(LocalDateTime.now());
        this.answerRepository.save(answer);

    }


    public void delete(Answer answer) {

        this.answerRepository.delete(answer);


    }


    public void vote(SiteUser siteUser, Answer answer) {
        answer.getVoter().add(siteUser);
        this.answerRepository.save(answer);
    }


    // 답변 컨트롤러에서 답변이 등록된 위치로 이동하기 위해서 --> 반드시 Answer 객체가 필요함 --> 서버가 답변 id를 알아함
// 기존에는 Answer 객체를 반환을 안함 --> 답변 id 몰라요 --> 앵거링이 불가능
    public Answer create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
        return answer;
    }









}
