import dao.order.Order;
import dao.order.OrderDaoImpl;
import dao.product.Product;
import dao.product.ProductDaoImpl;
import dao.product.category.CategoryDaoImpl;
import dao.user.User;
import dao.user.UserDaoImpl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
//        User user = new User();
//        user.setFirstName("1");
//        user.setLastName("1");
//        user.setPhone("1");
//        user.setEmail("1@gmail.com");
//        user.setAddress("1");

        Order order = new Order();
        order.setUser(new UserDaoImpl().selectById(1L));
        order.setActualOrder(true);
        order.setDateOrder(Timestamp.valueOf(LocalDateTime.now()));
        Map<Product, Integer> orderMap=new HashMap<>();
        orderMap.put(new ProductDaoImpl().selectById(2L), 3);
        orderMap.put(new ProductDaoImpl().selectById(1L), 5);
        orderMap.put(new ProductDaoImpl().selectById(3L), 9999);
        order.setOrderMap(orderMap);

        new OrderDaoImpl().insert(order);
//        new CategoryDaoImpl().list().forEach(category -> System.out.println(category.getIdCategory()));
//        User user =new User();
//        new UserDaoImpl().insert(user);
//        Category category=new Category();
//        category.setName("Трубы");
//        new CategoryDaoImpl().insert(category);
//
//        Product product = new Product();
//        product.setAmount(1);
//        product.setName("Батарея 1-секция, алюминий");
//        new ProductDaoImpl().insert(product);


//        User user=new User();
//        user.setFirstName("1");
//        user.setLastName("1");
//        user.setPhone("1");
//        user.setEmail("1@gmail.com");
//        user.setAddress("1");
//        new UserDaoImpl().insert(user);
//        System.out.println(user.getIdUser());
//        new UserDaoImpl().selectById(user.getIdUser()).getFirstName();
//        new UserDaoImpl().remove(user);
//        new UserDaoImpl().selectById(user.getIdUser()).getFirstName();

//        System.out.println(new UserDaoImpl().selectById(1).getFirstName());
//        System.out.println(new UserDaoImpl().selectById(1).getEmail());
//        System.out.println(new UserDaoImpl().selectById(1).getLogin());

//        Map<Product, Integer> set = new UserDaoImpl().selectById(1).getShoppingBasket();
//        set.keySet().forEach(a->System.out.print(a.getName()));
//        set.values().forEach(System.out::print);
    }
}
