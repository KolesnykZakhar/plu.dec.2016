package dao.user;


import java.util.List;

public interface UserDao {
    User selectById(Integer id);

    User selectByPhone(String phone);

    User selectByEmail(String email);

    User selectByLogin(String login);

    List<User> list();

    List<User> listOfRegisteredUsers();

    void update(User user);

    void remove(User user);

    void insert(User user);
}
