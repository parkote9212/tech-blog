package com.pgc.techblog.service;

import com.pgc.techblog.domain.Member;
import com.pgc.techblog.dto.MemberLoginRequest;
import com.pgc.techblog.dto.MemberSignupRequest;
import com.pgc.techblog.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//비스니스 로직을 담당하는 서비스 클래스임을 Spring에게 전달
@Service
// 아래 코드를 Lombok이 자동으로 생성
// public MemberService(MemberRepository memberRepository) {
//     this.memberRepository = memberRepository;
// }
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    //    아래 작업이 하나의 트랜잭션으로 처리
    @Transactional
    public Long signUp(MemberSignupRequest request) {
//        이메일 중복 확인
        if (memberRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }
//        닉네임 중복 확인
        if (memberRepository.findByNickname(request.getNickname()).isPresent()) {
            throw new IllegalArgumentException("이미 사용 중인 이메일 입니다.");
        }

        Member member = Member.builder()
                .email(request.getEmail())
//                비밀번호 암호화
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .build();


        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    public String login(MemberLoginRequest request){
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("가입되지않은 이메일입니다."));
        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
//        TODO : 로그인 성공시 JWT 토큰 생성 로직 추가 필요
        return "로그인 성공";
    }


}
