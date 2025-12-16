package com.mysite.sbb;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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



}
