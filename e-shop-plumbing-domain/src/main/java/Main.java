import dao.user.User;
import dao.user.UserDaoImpl;


public class Main {
    public static void main(String[] args) {
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


        User user=new User();
        user.setFirstName("1");
        user.setLastName("1");
        user.setPhone("1");
        user.setEmail("1@gmail.com");
        user.setAddress("1");
        new UserDaoImpl().insert(user);
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
