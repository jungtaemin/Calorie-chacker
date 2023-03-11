package com.tripproject.article.domain;

import com.tripproject.shared.domain.BaseEntity;
import com.tripproject.user.domain.User;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Getter
public class Article extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "article_id")
    private Long id;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 10000)
    private String content;

    private String thumbnailUrl;



    private Long hit;
    private Long bad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    protected Article() {
    }

    @Builder
    public Article(String title, String content, String thumbnailUrl,User user) {
        this.title = title;
        this.content = content;
        this.thumbnailUrl = thumbnailUrl;

        this.user =user;
        this.hit = 0L;
        this.bad = 0L;


    }

    public void edit(String content, String title){
        this.content = content;
        this.title = title;


    }

}
