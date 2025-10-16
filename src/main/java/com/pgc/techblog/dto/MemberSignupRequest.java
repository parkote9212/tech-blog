package com.pgc.techblog.dto;

// 회원가입시 클라이언트로부터 데이터를 담는 바구니 역할을 한다.

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberSignupRequest {
    private String email;
    private String password;
    private String nickname;
}
