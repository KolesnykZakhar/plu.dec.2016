package dao.order;

import dao.order.Order;
import dao.order.OrderDao;
import dao.order.OrderDaoImpl;
import dao.product.Product;
import dao.product.ProductDao;
import dao.product.ProductDaoImpl;
import dao.user.User;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class OrderDaoTest {
    @Test
    public void insert_selectById_update_list_remove_with_database_order() {
        //given
        OrderDao orderDao = new OrderDaoImpl();
        UserDao userDao = new UserDaoImpl();
        ProductDao productDao = new ProductDaoImpl();

        User userWithOrder = userDao.selectById(1);
        User userWithOutOrder = userDao.selectById(2);

        Order order = new Order();
        order.setUser(userWithOrder);
        order.setActualOrder(true);
        order.setDateOrder(Timestamp.valueOf(LocalDateTime.now()));
        Map<Product, Integer> orderMap = new HashMap<>();
        Product testProduct = productDao.selectById(1);
        orderMap.put(testProduct, 1111);
        orderMap.put(productDao.selectById(2), 5555);
        orderMap.put(productDao.selectById(3), 9999);
        order.setOrderMap(orderMap);

        //when
        orderDao.insert(order);

        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getMinutes(), order.getDateOrder().getMinutes());
        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getHours(), order.getDateOrder().getHours());
        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getDate(), order.getDateOrder().getDate());

        orderDao.listOrdersByUser(userWithOrder).forEach(orders ->
                assertEquals(orders.getUser().getIdUser(), userWithOrder.getIdUser()));
        orderDao.listOrdersByUser(userWithOutOrder).forEach(orders ->
                assertNotEquals(orders.getUser().getIdUser(), userWithOutOrder.getIdUser()));

        orderDao.listActualOrdersByUser(userWithOrder).forEach(orders ->
                assertEquals(orders.getUser().getIdUser(), userWithOrder.getIdUser()));
        orderDao.listActualOrdersByUser(userWithOutOrder).forEach(orders ->
                assertNotEquals(orders.getUser().getIdUser(), userWithOutOrder.getIdUser()));

        assertTrue(orderDao.listOrders().stream().anyMatch(
                orders -> orders.getIdOrder().equals(order.getIdOrder())));
        assertTrue(orderDao.listActualOrders().stream().anyMatch(
                orders -> orders.getIdOrder().equals(order.getIdOrder())));


        //when
        int sizeListOrder = orderDao.listOrders().size();
        int sizeListActualOrder = orderDao.listActualOrders().size();
        order.setActualOrder(false);
        order.getOrderMap().put(testProduct, 2222);
        orderDao.update(order);

        //then
        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getMinutes(), order.getDateOrder().getMinutes());
        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getHours(), order.getDateOrder().getHours());
        assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getDate(), order.getDateOrder().getDate());
        assertEquals(orderDao.selectById(order.getIdOrder()).getActualOrder(), false);
        assertEquals(sizeListOrder, orderDao.listOrders().size());
        assertEquals(sizeListActualOrder, orderDao.listActualOrders().size() + 1);
        assertTrue(orderDao.listOrders().stream().anyMatch(
                orders -> orders.getIdOrder().equals(order.getIdOrder())));
        assertFalse(orderDao.listActualOrders().stream().anyMatch(
                orders -> orders.getIdOrder().equals(order.getIdOrder())));

        //when
        orderDao.remove(order);

        //then
        assertEquals(sizeListOrder, orderDao.listOrders().size() + 1);
        assertEquals(sizeListActualOrder - 1, orderDao.listActualOrders().size());

        try {
            orderDao.selectById(order.getIdOrder()).getIdOrder();
            fail();
        } catch (NullPointerException ignore) {
        }
    }
}
