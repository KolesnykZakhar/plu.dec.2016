package dao.user;

import dao.product.Product;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface UserDao {
    User selectById(Integer id);

    List<User> list();

    void update(User user);

    void remove(User user);

    void insert(User user);
}
