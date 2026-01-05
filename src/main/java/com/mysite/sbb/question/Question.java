package com.mysite.sbb.question;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Question {
    @Id     //db 테이블의 pk(주키) 로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)             //  신규 레코드가(행) insert 될때 자동 ID 값을 1증가.
    private Integer id;         // null 체크를 위해 int 가 아닌 래퍼클래서 Integer를 사용
                                // 기본형은 null 개념이 없으므로 래퍼클래스 사용

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")      // field 의 타입을 text로 지정 > mysql 은varchar로 들어감
    private String content;

    private LocalDateTime createDate;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)    // 지워지면 댓글까지 지우도록
    private List<Answer> answerList;       //복수개의 답변을 저장하려면 list 구조 >  제너릭 으로 Answer 타입 지정.

    @ManyToOne
    private SiteUser author;  // 한명의 글쓴이가 여러 질문 등록 가능


    private LocalDateTime modifyDate ;      //수정 일시 추가하기
    
    
    @ManyToMany
    Set<SiteUser> voter;     //중목 혀용 안되는 컬렉션 프레임워크인 Set 을 활용하여 1유저가 1번만 등록 가능
    




}
