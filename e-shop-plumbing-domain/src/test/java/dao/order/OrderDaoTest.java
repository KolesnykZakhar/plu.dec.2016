package dao.order;

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

import static dao.DatabaseToolsForTesting.MESSAGE_NOT_ENDED_FAIL_TEST;
import static org.junit.Assert.*;

public class OrderDaoTest {
    private static final int ID_OF_FIRST_USER_IN_DB = 1;
    private static final int ID_OF_SECOND_USER_IN_DB = 2;
    private static final int ID_FIRST_PRODUCT_IN_DB = 1;
    private static final int ID_OF_SECOND_PRODUCT_IN_DB = 2;
    private static final int ID_OF_THIRD_PRODUCT_IN_DB = 3;
    private static final int TEST_AMOUNT_OF_FIRST_PRODUCT_IN_BASKET = 1111;
    private static final int TEST_AMOUNT_OF_SECOND_PRODUCT_IN_BASKET = 5555;
    private static final int TEST_AMOUNT_OF_THIRD_PRODUCT_IN_BASKET = 9999;
    private static final int TEST_UPDATE_AMOUNT_OF_FIRST_PRODUCT_IN_BASKET = 2222;
    private static final int AMOUNT_OF_NOT_ACTUAL_ORDERS = 1;

    @Test
    public void insert_selectById_update_list_remove_with_database_order() {
        //given
        OrderDao orderDao = new OrderDaoImpl();
        UserDao userDao = new UserDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        Order order = new Order();

        try {

            User userWithOrder = userDao.selectById(ID_OF_FIRST_USER_IN_DB);
            User userWithOutOrder = userDao.selectById(ID_OF_SECOND_USER_IN_DB);

            order.setUser(userWithOrder);
            order.setActualOrder(true);
            order.setDateOrder(Timestamp.valueOf(LocalDateTime.now()));
            Map<Product, Integer> orderMap = new HashMap<>();
            Product testProduct = productDao.selectById(ID_FIRST_PRODUCT_IN_DB);

            orderMap.put(testProduct, TEST_AMOUNT_OF_FIRST_PRODUCT_IN_BASKET);
            orderMap.put(productDao.selectById(ID_OF_SECOND_PRODUCT_IN_DB), TEST_AMOUNT_OF_SECOND_PRODUCT_IN_BASKET);
            orderMap.put(productDao.selectById(ID_OF_THIRD_PRODUCT_IN_DB), TEST_AMOUNT_OF_THIRD_PRODUCT_IN_BASKET);
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
            order.getOrderMap().put(testProduct, TEST_UPDATE_AMOUNT_OF_FIRST_PRODUCT_IN_BASKET);
            orderDao.update(order);

            //then
            assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getMinutes(), order.getDateOrder().getMinutes());
            assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getHours(), order.getDateOrder().getHours());
            assertEquals(orderDao.selectById(order.getIdOrder()).getDateOrder().getDate(), order.getDateOrder().getDate());
            assertEquals(orderDao.selectById(order.getIdOrder()).getActualOrder(), false);
            assertEquals(sizeListOrder, orderDao.listOrders().size());

            assertEquals(sizeListActualOrder, orderDao.listActualOrders().size() + AMOUNT_OF_NOT_ACTUAL_ORDERS);
            assertTrue(orderDao.listOrders().stream().anyMatch(
                    orders -> orders.getIdOrder().equals(order.getIdOrder())));
            assertFalse(orderDao.listActualOrders().stream().anyMatch(
                    orders -> orders.getIdOrder().equals(order.getIdOrder())));

            //when
            orderDao.remove(order);

            //then
            assertEquals(sizeListOrder, orderDao.listOrders().size() + AMOUNT_OF_NOT_ACTUAL_ORDERS);
            assertEquals(sizeListActualOrder - AMOUNT_OF_NOT_ACTUAL_ORDERS, orderDao.listActualOrders().size());

            try {
                orderDao.selectById(order.getIdOrder()).getIdOrder();
                fail();
            } catch (NullPointerException ignore) {
            }
        } catch (Exception e) {
            if (order.getIdOrder() != null) {
                orderDao.remove(order);
            }
            fail(MESSAGE_NOT_ENDED_FAIL_TEST);
        }
    }
}
