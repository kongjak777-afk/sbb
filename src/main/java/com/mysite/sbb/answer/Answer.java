package com.mysite.sbb.answer;


import com.mysite.sbb.question.Question;
import com.mysite.sbb.user.SiteUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;




    @Column(columnDefinition = "TEXT")      // field 의 타입을 text로 지정 > mysql 은varchar로 들어감
    private String content;

    private LocalDateTime createDate;


    @ManyToOne
    private Question question;


    @ManyToOne
    private SiteUser author;  // 한명의 글쓴이가 여러 답변 등록 가능



    
    private LocalDateTime modifyDate ;      //수정 일시 추가하기


    @ManyToMany
    Set<SiteUser> voter;     //중목 혀용 안되는 컬렉션 프레임워크인 Set 을 활용하여 1유저가 1번만 등록 가능













}
