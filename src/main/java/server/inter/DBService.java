package server.inter;

import server.service.UserEntity;

public interface DBService {

    UserEntity findUser(String login);
    boolean add(UserEntity user);
    void updateUserName(UserEntity user, String newName);
}
