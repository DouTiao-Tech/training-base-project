package com.darcytech.training.api;

import java.util.List;

public interface UserService {

    String findUsernameById(long userId);

    User findById(long userId);

    List<User> findByName(String name);

    User save(User user);

}
