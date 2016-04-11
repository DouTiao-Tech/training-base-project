package com.darcytech.training.web.dubbo;

import com.darcytech.training.api.UserDubboService;

public class UserDubboServiceImpl implements UserDubboService {

    @Override
    public String findUsernameById(long userId) {
        return "name: " + userId;
    }

}
