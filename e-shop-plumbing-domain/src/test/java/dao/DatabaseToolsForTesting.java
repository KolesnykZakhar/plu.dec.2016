package dao;

import dao.product.Product;
import dao.product.ProductDaoImpl;
import dao.product.category.Category;
import dao.product.category.CategoryDaoImpl;
import dao.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public class DatabaseToolsForTesting {
    public static final String MESSAGE_NOT_ENDED_FAIL_TEST = "test not ended";
    private static final String FIRST_NAME_COLUMN = "firstName";
    private static final String FIRST_NAME_TEST = "FirstNameTEST";
    private static final String FIRST_NAME_TEST_UPDATE = "FirstNameTEST-UPDATE";
    private static final String NAME_PRODUCT_COLUMN = "nameProduct";
    private static final String PRODUCT_NAME_TEST = "productNameTEST";
    private static final String PRODUCT_NAME_TEST_UPDATE = "productNameTEST-UPDATE";
    private static final String NAME_CATEGORY_COLUMN = "nameCategory";
    private static final String CATEGORY_NAME_TEST = "CategoryNameTEST";
    private static final String CATEGORY_NAME_TEST_UPDATE = "CategoryNameTEST-UPDATE";
    private static final String FIRST_TEST_ATTRIBUTE_DESCRIPTION = "1st TEST-attribute";
    private static final String SECOND_TEST_ATTRIBUTE_DESCRIPTION = "2st TEST-attribute";
    private static final String THIRD_TEST_ATTRIBUTE_DESCRIPTION = "3st TEST-attribute";
    private static final String PARAMETER_OF_FIRST_TEST_ATTRIBUTE = "parameter of 1st TEST-attribute";
    private static final String PARAMETER_OF_SECOND_TEST_ATTRIBUTE = "parameter of 2st TEST-attribute";
    private static final String PARAMETER_OF_THIRD_TEST_ATTRIBUTE = "parameter of 3st TEST-attribute";
    private static final String PATH_FOR_TO_THE_PICTURES = "src/test/resources/pictures/";
    private static final String EXTENSION_OF_ALL_PICTURES = ".jpg";
    private static final int TEST_AMOUNT_OF_NEW_PRODUCT = 1111;
    private static final double TEST_PRICE_OF_NEW_PRODUCT = 111.11;
    private static final int AMOUNT_OF_PICTURES_FOR_THE_TEST = 10;
    private static final int TEST_AMOUNT_OF_PRODUCTS_IN_BASKET = 1111;
    private static final String FIRST_NAME_USER_TEST = "FirstNameTEST";
    private static final String LAST_NAME_USER_TEST = "LastNameTEST";
    private static final String PHONE_USER_TEST = "PhoneTEST";
    private static final String EMAIL_USER_TEST = "email@TEST";
    private static final String ADDRESS_USER_TEST = "AddressTEST";
    private static final String LOGIN_USER_TEST = "LoginTEST";
    private static final String PASSWORD_USER_TEST = "PasswordTEST";
    private static final String FIRST_NAME_USER_TEST_UPDATE = "FirstNameTEST-UPDATE";
    private static final String LAST_NAME_USER_TEST_UPDATE = "LastNameTEST-UPDATE";
    private static final String PHONE_USER_TEST_UPDATE = "PhoneTEST-UPDATE";
    private static final String EMAIL_USER_TEST_UPDATE = "email@TEST-UPDATE";
    private static final String ADDRESS_USER_TEST_UPDATE = "AddressTEST-UPDATE";
    private static final String LOGIN_USER_TEST_UPDATE = "LoginTEST-UPDATE";
    private static final String PASSWORD_USER_TEST_UPDATE = "PasswordTEST-UPDATE";

    public static void clearDatabaseFromTestingRows() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {

            //clear user
            User user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq(FIRST_NAME_COLUMN, FIRST_NAME_TEST)).uniqueResult();
            if (user != null) {
                session.delete(user);
            }
            User userUpdate = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq(FIRST_NAME_COLUMN, FIRST_NAME_TEST_UPDATE)).uniqueResult();
            if (userUpdate != null) {
                session.delete(userUpdate);
            }

            //clear product
            Product product = (Product) session.createCriteria(Product.class)
                    .add(Restrictions.eq(NAME_PRODUCT_COLUMN, PRODUCT_NAME_TEST)).uniqueResult();
            if (product != null) {
                session.delete(product);
            }
            Product productUpdate = (Product) session.createCriteria(Product.class)
                    .add(Restrictions.eq(NAME_PRODUCT_COLUMN, PRODUCT_NAME_TEST_UPDATE)).uniqueResult();
            if (productUpdate != null) {
                session.delete(productUpdate);
            }

            //clear category
            Category category = (Category) session.createCriteria(Category.class)
                    .add(Restrictions.eq(NAME_CATEGORY_COLUMN, CATEGORY_NAME_TEST)).uniqueResult();
            if (category != null) {
                session.delete(category);
            }
            Category categoryUpdate = (Category) session.createCriteria(Category.class)
                    .add(Restrictions.eq(NAME_CATEGORY_COLUMN, CATEGORY_NAME_TEST_UPDATE)).uniqueResult();
            if (categoryUpdate != null) {
                session.delete(categoryUpdate);
            }

            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public static void initializeCategory(Category category) {
        category.setNameCategory(CATEGORY_NAME_TEST);
    }

    public static void updateFieldOfCategory(Category category) {
        category.setNameCategory(CATEGORY_NAME_TEST_UPDATE);
    }

    public static void initializeProduct(Product product) {

        //initialize map with description
        Map<String, String> descriptionMock = new HashMap<>();
        descriptionMock.put(FIRST_TEST_ATTRIBUTE_DESCRIPTION, PARAMETER_OF_FIRST_TEST_ATTRIBUTE);
        descriptionMock.put(SECOND_TEST_ATTRIBUTE_DESCRIPTION, PARAMETER_OF_SECOND_TEST_ATTRIBUTE);
        descriptionMock.put(THIRD_TEST_ATTRIBUTE_DESCRIPTION, PARAMETER_OF_THIRD_TEST_ATTRIBUTE);

        //initialize map with pictures
        Map<Integer, byte[]> picturesMock = new HashMap<>();

        try {
            for (int i = 0; i < AMOUNT_OF_PICTURES_FOR_THE_TEST; i++) {
                StringBuilder namePic = new StringBuilder(PATH_FOR_TO_THE_PICTURES)
                        .append(i).append(EXTENSION_OF_ALL_PICTURES);
                byte[] b;
                FileInputStream file = new FileInputStream
                        (new File(namePic.toString()));
                ByteArrayOutputStream arr = new ByteArrayOutputStream();
                GZIPOutputStream gzip = new GZIPOutputStream(arr);
                try {
                    int c;
                    while ((c = file.read()) != -1) {
                        gzip.write(c);
                    }
                    b = arr.toByteArray();
                } finally {
                    file.close();
                    arr.flush();
                    arr.close();
                }
                picturesMock.put(i, b);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //initialize category
        Category category = new Category();
        initializeCategory(category);
        new CategoryDaoImpl().insert(category);

        //initialize product
        product.setNameProduct(PRODUCT_NAME_TEST);
        product.setAmount(TEST_AMOUNT_OF_NEW_PRODUCT);
        product.setPrice(TEST_PRICE_OF_NEW_PRODUCT);
        product.setCategory(category);
        product.setDescription(descriptionMock);
        product.setPictures(picturesMock);
    }

    public static void updateFieldOfProduct(Product product) {
        product.setNameProduct(PRODUCT_NAME_TEST_UPDATE);
    }

    public static void initializeUser(User user, Product product) {

        //initialize product
        initializeProduct(product);
        new ProductDaoImpl().insert(product);

        //initialize shopping basket map
        Map<Product, Integer> shoppingBasket = new HashMap<>();
        shoppingBasket.put(product, TEST_AMOUNT_OF_PRODUCTS_IN_BASKET);

        //initialize wish list set
        Set<Product> wishList = new HashSet<>();
        wishList.add(product);

        //initialize user
        user.setFirstName(FIRST_NAME_USER_TEST);
        user.setLastName(LAST_NAME_USER_TEST);
        user.setPhone(PHONE_USER_TEST);
        user.setEmail(EMAIL_USER_TEST);
        user.setAddress(ADDRESS_USER_TEST);
        user.setLoginUser(LOGIN_USER_TEST);
        user.setPasswordUser(PASSWORD_USER_TEST);
        user.setWishList(wishList);
        user.setShoppingBasket(shoppingBasket);
    }

    public static void updateFieldsOfUser(User user) {
        user.setFirstName(FIRST_NAME_USER_TEST_UPDATE);
        user.setLastName(LAST_NAME_USER_TEST_UPDATE);
        user.setPhone(PHONE_USER_TEST_UPDATE);
        user.setEmail(EMAIL_USER_TEST_UPDATE);
        user.setAddress(ADDRESS_USER_TEST_UPDATE);
        user.setLoginUser(LOGIN_USER_TEST_UPDATE);
        user.setPasswordUser(PASSWORD_USER_TEST_UPDATE);
    }
}
