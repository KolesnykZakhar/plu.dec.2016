package dao;

import dao.category.Category;
import dao.category.CategoryDao;
import dao.category.CategoryDaoImpl;
import dao.product.Product;
import dao.product.ProductDao;
import dao.product.ProductDaoImpl;
import dao.user.User;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import static dao.Initializer.initializeUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class DaoTest {
    @Test
    public void selectById_insert_remove_with_database_category_product_user() throws Exception {

        //given
        UserDao userDao = new UserDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();

        User user = new User();
        Product product = new Product();
        Category category = new Category();

        //initialization
        initializeUser(user, product, category);

        //when
        userDao.insert(user);

        //then
        assertEquals(userDao.selectById(user.getIdUser()).getFirstName(), (user.getFirstName()));
        assertEquals(userDao.selectById(user.getIdUser()).getLastName(), (user.getLastName()));
        assertEquals(userDao.selectById(user.getIdUser()).getPhone(), (user.getPhone()));
        assertEquals(userDao.selectById(user.getIdUser()).getEmail(), (user.getEmail()));
        assertEquals(userDao.selectById(user.getIdUser()).getAddress(), (user.getAddress()));
        assertEquals(userDao.selectById(user.getIdUser()).getLogin(), (user.getLogin()));
        assertEquals(userDao.selectById(user.getIdUser()).getPassword(), (user.getPassword()));
        assertEquals(userDao.selectById(user.getIdUser()).getPassword(), (user.getPassword()));

        //when
        userDao.remove(user);
        productDao.remove(product);
        categoryDao.remove(category);

        //then
        try {
            userDao.selectById(user.getIdUser()).getFirstName();
            fail();
        } catch (ObjectNotFoundException ignore) {
        }
        try {
            productDao.selectById(product.getIdProduct()).getName();
            fail();
        } catch (ObjectNotFoundException ignore) {
        }
        try {
            categoryDao.selectById(category.getIdCategory()).getName();
            fail();
        } catch (ObjectNotFoundException ignore) {
        }
    }

}
