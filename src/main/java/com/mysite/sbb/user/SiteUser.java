package com.mysite.sbb.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SiteUser {
    @Id     //db 테이블의 pk(주키) 로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;     // 기본형은 null 개념이 없으므로 래퍼클래스 사용

    @Column(unique = true)          // 중복 허용 안됨. 중복되지 않는 값만 들어올 수 있음
    private String userName;        // 여기서 유저네임은 로그인시 사용하는 ID

    private String password;

    @Column(unique = true)          // 중복 허용 안됨. 중복되지 않는 값만 들어올 수 있음
    private String email;

}
