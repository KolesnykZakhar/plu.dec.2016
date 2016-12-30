package dao.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.user.User;

import java.util.List;

public interface OrderDao {
    Order selectById(Integer id);
    List<Order> listOrdersByUser(User user);
    List<Order> listActualOrdersByUser(User user);
    void insert(Order order);
    void remove(Order order);
    void update(Order order);

}
