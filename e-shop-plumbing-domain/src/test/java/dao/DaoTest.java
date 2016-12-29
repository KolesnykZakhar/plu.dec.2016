package dao;

import dao.product.category.Category;
import dao.product.category.CategoryDao;
import dao.product.category.CategoryDaoImpl;
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
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();

        User user = new User();
        Category category = new Category();
        Product product = new Product();

        //when
        initializeUser(user, category, product);

        //then
        assertEquals(userDao.selectById(user.getIdUser()).getFirstName(), (user.getFirstName()));
        assertEquals(userDao.selectById(user.getIdUser()).getLastName(), (user.getLastName()));
        assertEquals(userDao.selectById(user.getIdUser()).getPhone(), (user.getPhone()));
        assertEquals(userDao.selectById(user.getIdUser()).getEmail(), (user.getEmail()));
        assertEquals(userDao.selectById(user.getIdUser()).getAddress(), (user.getAddress()));
        assertEquals(userDao.selectById(user.getIdUser()).getLoginUser(), (user.getLoginUser()));
        assertEquals(userDao.selectById(user.getIdUser()).getPasswordUser(), (user.getPasswordUser()));
        assertEquals(userDao.selectById(user.getIdUser()).getShoppingBasket().get(new ProductDaoImpl().selectById(product.getIdProduct())),
                (user.getShoppingBasket().get(new ProductDaoImpl().selectById(product.getIdProduct()))));

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
