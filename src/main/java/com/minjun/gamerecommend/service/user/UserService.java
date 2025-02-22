package com.minjun.gamerecommend.service.user;

import com.minjun.gamerecommend.domain.user.UserFinder;
import com.minjun.gamerecommend.domain.user.UserId;
import com.minjun.gamerecommend.domain.user.UserSaveCommand;
import com.minjun.gamerecommend.domain.user.UserStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserFinder userFinder;
    private final UserStorage userStorage;

    public UserId saveUser(String userId) {
        HashMap<String, Object> userDetail = userFinder.findUserDetail(userId);

        UserSaveCommand userSaveCommand = UserSaveCommand.from(userDetail);
        String saveUserId = userStorage.saveUser(userSaveCommand);

        return UserId.from(saveUserId);
    }
}
