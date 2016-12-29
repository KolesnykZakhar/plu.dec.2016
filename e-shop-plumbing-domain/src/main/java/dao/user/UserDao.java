package dao.user;


import java.util.List;

public interface UserDao {
    User selectById(Long id);

    List<User> list();

    void update(User user);

    void remove(User user);

    void insert(User user);
}
