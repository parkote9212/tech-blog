package com.pgc.techblog.controller;

import com.pgc.techblog.dto.PostCreateRequest;
import com.pgc.techblog.repository.PostRepository;
import com.pgc.techblog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(
            @RequestBody PostCreateRequest request,
            @AuthenticationPrincipal UserDetails userDetails){

//        1. 현재 로그인한 사용자의 이메일 정보 추출
        String authorEmail = userDetails.getUsername();

//        2. 서비스를 호출하여 게시글 생성
        Long postId = postService.createPost(authorEmail, request);
    }


}
