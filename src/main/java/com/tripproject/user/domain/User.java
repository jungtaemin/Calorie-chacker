package com.tripproject.user.domain;


import com.tripproject.article.domain.Article;
import lombok.Builder;
import lombok.Getter;
import com.tripproject.shared.domain.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;


    private String usersId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String provider;
    private String providerId;

    private String picURI;


    @OneToMany(mappedBy = "user")
    private List<Article> articles= new ArrayList<>();

    protected User() {
    }

    @Builder
    public User(String usersId, String userName, String email, Role role, String provider, String providerId, String picURI) {
        this.usersId = usersId;
        this.userName = userName;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
        this.picURI = picURI;
    }


    public void renewUsername(String username) {
        if(!this.userName.equals(username))
            this.userName = username;
    }
}
