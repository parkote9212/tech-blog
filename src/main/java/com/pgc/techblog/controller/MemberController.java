package com.pgc.techblog.controller;

import com.pgc.techblog.dto.MemberLoginRequest;
import com.pgc.techblog.dto.MemberSignupRequest;
import com.pgc.techblog.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/me")
    public ResponseEntity<String> getMyInfo(@AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();
        return ResponseEntity.ok("인증 성공! 당신의 이메일은 " + email + " 입니다.");
    }

}
