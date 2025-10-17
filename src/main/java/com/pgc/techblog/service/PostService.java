package com.pgc.techblog.service;

import com.pgc.techblog.domain.Member;
import com.pgc.techblog.domain.Post;
import com.pgc.techblog.dto.PostCreateRequest;
import com.pgc.techblog.repository.MemberRepository;
import com.pgc.techblog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public Long createPost(String authorEmail, PostCreateRequest request) {
//      1. 작성자 정보 조회
        Member author = memberRepository.findByEmail(authorEmail)
                .orElseThrow(() -> new IllegalArgumentException("작성자를 찾을 수가 없습니다."));
//      2. Post 객체 생성
        Post post = Post.builder()
                .author(author)
                .title(request.getTitle())
                .content(request.getContent())
                .build();

        Post savedPost = postRepository.save(post);

        return savedPost.getId();
    }

}
