package com.minjun.gamerecommend.service.user.process;

import com.minjun.gamerecommend.domain.user.User;
import com.minjun.gamerecommend.domain.user.UserDao;
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
