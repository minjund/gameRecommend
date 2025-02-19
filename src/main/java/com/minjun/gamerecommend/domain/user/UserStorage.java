package com.minjun.gamerecommend.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserStorage {

    private final UserDao userDao;

    public String saveUser(UserSaveCommand userSaveCommand) {
        User user = User.from(userSaveCommand);

        return userDao.save(user).getId();
    }
}
