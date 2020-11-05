package server.inter;

import server.service.UserEntity;

public interface AuthService {

    void start();
    UserEntity getUser(String login, String password);
    void stop();

}
