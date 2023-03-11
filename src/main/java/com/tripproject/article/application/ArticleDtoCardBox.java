package com.tripproject.article.application;


import com.tripproject.user.domain.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter @Setter
public class ArticleDtoCardBox {

    private Long id;


    private String title;


    private String content;

    private String thumbnailUrl;

    private LocalDateTime createdDate;

    private User user;

    private Long hit;

    private Long bad;

    private String userName;

}
