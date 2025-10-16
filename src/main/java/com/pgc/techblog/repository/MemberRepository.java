package com.pgc.techblog.repository;

import com.pgc.techblog.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JpaRepository를 **상속(extends)**받는 것만으로도 기본적인 DB操作(조작) 메서드들이 자동으로 구현됩니다.
//
//<Member, Long>은 이 리포지토리가 Member 엔티티를 관리하며, Member 엔티티의 **기본 키(PK) 타입이 Long**이라는 것을 의미합니다.
public interface MemberRepository extends JpaRepository<Member,Long> {


    Optional<Member> findByEmail(String email);
    Optional<Member> findByNickname(String nickname);
}
