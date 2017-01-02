package dao.order;


import dao.user.User;

import java.util.List;

public interface OrderDao {
    Order selectById(Integer id);

    List<Order> listOrders();

    List<Order> listActualOrders();

    List<Order> listOrdersByUser(User user);

    List<Order> listActualOrdersByUser(User user);

    void insert(Order order);

    void remove(Order order);

    void update(Order order);

}
