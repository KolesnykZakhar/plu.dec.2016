package dao;

import dao.product.category.CategoryDao;
import dao.product.category.CategoryDaoImpl;
import dao.product.Product;
import dao.product.ProductDao;
import dao.product.ProductDaoImpl;
import dao.user.User;
import dao.user.UserDao;
import dao.user.UserDaoImpl;
import org.junit.Test;

import static dao.DatabaseTools.initializeUser;
import static dao.DatabaseTools.updateFieldsOfUser;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class UserDaoTest {
    @Test
    public void selectById_insert_remove_with_database_category_product_user() throws Exception {

        //given
        UserDao userDao = new UserDaoImpl();
        CategoryDao categoryDao = new CategoryDaoImpl();
        ProductDao productDao = new ProductDaoImpl();
        User user = new User();
        Product product = new Product();

        //when
        initializeUser(user, product);
        userDao.insert(user);

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
        assertTrue(userDao.list().stream().anyMatch(
                users -> users.getIdUser().equals(user.getIdUser())));

        //when
        updateFieldsOfUser(user);
        userDao.update(user);

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
        assertTrue(userDao.list().stream().anyMatch(
                users -> users.getIdUser().equals(user.getIdUser())));

        //when
        userDao.remove(user);
        productDao.remove(product);
        categoryDao.remove(product.getCategory());

        //then
        try {
            userDao.selectById(user.getIdUser()).getFirstName();
            fail();
        } catch (NullPointerException ignore) {
        }
        try {
            productDao.selectById(product.getIdProduct()).getNameProduct();
            fail();
        } catch (NullPointerException ignore) {
        }
        try {
            categoryDao.selectById(product.getCategory().getIdCategory()).getNameCategory();
            fail();
        } catch (NullPointerException ignore) {
        }
    }
}
