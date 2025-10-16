package com.pgc.techblog.controller;

import com.pgc.techblog.dto.MemberLoginRequest;
import com.pgc.techblog.dto.MemberSignupRequest;
import com.pgc.techblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


// REST API를 처리
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")

public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody MemberSignupRequest request) {
        Long memberId = memberService.signUp(request);
        return ResponseEntity.ok("회원가입이 완료되었습니다. 회원 ID: " + memberId);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MemberLoginRequest request){
        String result = memberService.login(request);
        return  ResponseEntity.ok(result);
    }
}
