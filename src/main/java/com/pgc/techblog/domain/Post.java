package com.pgc.techblog.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access =AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne(fetch =  FetchType.LAZY) // 지연 로딩
    @JoinColumn(name = "member_id")  // 외래 키
    private  Member author;

    @Column(nullable = false)
    private String title;

    @Lob //대용량 텍스트를 저장
    @Column(nullable = false)
    private String content;

    @Builder
    public  Post(Member author, String title, String content){
        this.author = author;
        this.title = title;
        this.content = content;
    }
}
