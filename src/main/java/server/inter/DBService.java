package server.inter;

import server.service.UserEntity;

import java.util.ArrayList;

public interface DBService {

    ArrayList<UserEntity> findAll();
    UserEntity findUser(String login);
    boolean add(UserEntity user);
    boolean updateUserName(UserEntity user);
}
