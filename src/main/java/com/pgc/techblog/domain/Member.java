package com.pgc.techblog.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 데이터베이스 테이블과 매핑되는 엔티티임을 나타냄
@Entity
@Getter
// 파라미터가없는 생성자 자동
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    // 이 필드가 테이블의 기본 키임을 나타냅니다.
    @Id
//    기본 키값을 데이터베이스가 자동으로 생성
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    필드를 테이브르이 컬럼과 매핑합니다.
    @Column(name = "member_id")
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String nickname;

//    빌더 패턴을 사용해 객체 생성
    @Builder
    public Member(String email, String password, String nickname){
    this.email = email;
    this.password = password;
    this.nickname = nickname;
    }
}
