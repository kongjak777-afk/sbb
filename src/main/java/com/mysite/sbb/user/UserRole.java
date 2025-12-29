package com.mysite.sbb.user;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN_ROLE"),            //대문자는 상수를 의미
    USER("USER_ROLE");

    UserRole(String value) {                // 여기 값에 어드민과 유저 롤이 들어옴
        this.value = value;
    }

    private String value;
}