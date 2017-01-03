package dao.user;

import dao.product.Product;
import dao.product.ProductDao;
import dao.product.ProductDaoImpl;
import dao.product.category.CategoryDao;
import dao.product.category.CategoryDaoImpl;
import org.junit.Test;

import static dao.DatabaseToolsForTesting.*;
import static org.junit.Assert.*;

public class UserDaoTest {
    @Test
    public void selectByLoginUser() throws Exception {
        //given
        User userExpected = new UserDaoImpl().selectById(1);

        //when
        User userActual = new UserDaoImpl().selectByLogin(userExpected.getLoginUser());

        //then
        assertEquals(userExpected, userActual);
    }

    @Test
    public void selectByEmailUser() throws Exception {
        //given
        User userExpected = new UserDaoImpl().selectById(1);

        //when
        User userActual = new UserDaoImpl().selectByEmail(userExpected.getEmail());

        //then
        assertEquals(userExpected, userActual);
    }

    @Test
    public void selectByPhoneUser() throws Exception {

        //given
        User userExpected = new UserDaoImpl().selectById(1);

        //when
        User userActual = new UserDaoImpl().selectByPhone(userExpected.getPhone());

        //then
        assertEquals(userExpected, userActual);
    }

    @Test
    public void listOfRegisteredUsers() throws Exception {
        //given
        UserDao userDao = new UserDaoImpl();

        //when, then
        assertFalse(userDao.listOfRegisteredUsers().stream().anyMatch(
                users -> users.getLoginUser() == null));
    }

    @Test
    public void selectById_insert_remove_with_database_category_product_user() throws Exception {

        try {
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
        } catch (Exception e) {
            clearDatabaseFromTestingRows();
            fail(MESSAGE_NOT_ENDED_FAIL_TEST);
        }
    }
}
