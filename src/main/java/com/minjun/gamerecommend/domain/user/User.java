package com.minjun.gamerecommend.domain.user;

import com.minjun.gamerecommend.service.user.process.UserSaveCommand;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Entity
@Getter
@Table(name = "user")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
    private Long seq;

    @Column(name = "id")
    private String id;

    @Column(name = "nickname")
    private String nickName;

    public static User from(UserSaveCommand userSaveCommand) {
        User user = new User();

        user.id = userSaveCommand.userId();
        user.nickName = userSaveCommand.nickname();

        return user;
    }
}
