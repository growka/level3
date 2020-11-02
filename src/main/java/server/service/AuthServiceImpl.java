package server.service;

import server.inter.AuthService;

import java.util.List;

public class AuthServiceImpl implements AuthService {

    private List<UserEntity> userList;

    public AuthServiceImpl() {
//        this.userList = new LinkedList<>();
//        this.userList.add(new UserEntity("nick1","login1", "passw1"));
//        this.userList.add(new UserEntity("nick2","login2", "passw2"));
//        this.userList.add(new UserEntity("nick3","login3", "passw3"));
    }

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public UserEntity getUser(String login, String password) {
        DBServiceImpl dbServiceimpl = new DBServiceImpl();
        UserEntity user = dbServiceimpl.findUser(login);
        if (user != null) {
            if ((user.getName().equals(login)) & (user.getPassword().equals(password))) {
                return user;
            }
        }
        return null;
    }



    @Override
    public void stop() {

    }

}
