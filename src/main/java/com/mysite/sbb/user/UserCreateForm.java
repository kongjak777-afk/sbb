package com.mysite.sbb.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateForm {

    @NotBlank(message = "아이디는 필수 항목입니다. 최소 3글자 최대 25")
    @Size(min = 3, max = 25)
    public String username;

    @NotBlank(message = "Password는 필수 항목입니다.")
    public String password1;

    @NotBlank(message = "Password를 한번 더 확인 해주세요.")
    public String password2;

    @NotBlank(message = "이메일은 필수 항목입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.")
    public String email;




}
