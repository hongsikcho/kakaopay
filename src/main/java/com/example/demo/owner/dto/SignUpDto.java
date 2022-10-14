package com.example.demo.owner.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Data
public class SignUpDto {

    @NotEmpty(message = "아이디는 필수 항목입니다.")
    private String id;

    @NotEmpty(message = "이름은 필수 항목입니다.")
    private String name;

    @NotEmpty(message = "비밀번호는 필수 항목입니다.")
    private String password;

    @NotEmpty(message = "비밀번호 확인은 필수 항목입니다.")
    private String password2;

    @NotEmpty(message = "전화번호는 필수 항목입니다.")
    private String number;
}
