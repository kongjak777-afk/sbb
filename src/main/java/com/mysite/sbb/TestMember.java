package com.mysite.sbb;

import lombok.Getter;
import lombok.Setter;

@Getter             // lombok
@Setter             // 게터 세터를 지정하면 클래스에 적용되어 따로 안적을 수 있다.
public class TestMember {
    private String name;


//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
}
