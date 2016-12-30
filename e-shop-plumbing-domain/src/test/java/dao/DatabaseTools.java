package dao;

import dao.product.category.Category;
import dao.product.category.CategoryDaoImpl;
import dao.product.Product;
import dao.product.ProductDaoImpl;
import dao.user.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.junit.Ignore;
import org.junit.Test;

import java.io.*;
import java.util.*;
import java.util.zip.GZIPOutputStream;

public class DatabaseTools {

    @Ignore
    @Test
    public void clearDatabaseFromTestingRows() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        try {

            //clear user
            User user = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("firstName", "FirstNameTEST")).uniqueResult();
            if (user != null) {
                session.delete(user);
            }
            User userUpdate = (User) session.createCriteria(User.class)
                    .add(Restrictions.eq("firstName", "FirstNameTEST-UPDATE")).uniqueResult();
            if (userUpdate != null) {
                session.delete(userUpdate);
            }

            //clear product
            Product product = (Product) session.createCriteria(Product.class)
                    .add(Restrictions.eq("nameProduct", "productNameTEST")).uniqueResult();
            if (product != null) {
                session.delete(product);
            }
            Product productUpdate = (Product) session.createCriteria(Product.class)
                    .add(Restrictions.eq("nameProduct", "productNameTEST-UPDATE")).uniqueResult();
            if (productUpdate != null) {
                session.delete(productUpdate);
            }

            //clear category
            Category category = (Category) session.createCriteria(Category.class)
                    .add(Restrictions.eq("nameCategory", "CategoryNameTEST")).uniqueResult();
            if (category != null) {
                session.delete(category);
            }
            Category categoryUpdate = (Category) session.createCriteria(Category.class)
                    .add(Restrictions.eq("nameCategory", "CategoryNameTEST-UPDATE")).uniqueResult();
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
        category.setNameCategory("CategoryNameTEST");
    }

    public static void updateFieldOfCategory(Category category) {
        category.setNameCategory("CategoryNameTEST-UPDATE");
    }

    public static void initializeProduct(Product product/*, Category category*/) {

        //initialize map with description
        Map<String, String> descriptionMock = new HashMap<>();
        descriptionMock.put("1st TEST-attribute", "parameter of 1st TEST-attribute");
        descriptionMock.put("2st TEST-attribute", "parameter of 2st TEST-attribute");
        descriptionMock.put("3st TEST-attribute", "parameter of 3st TEST-attribute");

        //initialize map with pictures
        Map<Integer, byte[]> picturesMock = new HashMap<>();

        try {
            for (int i = 0; i < 10; i++) {
                StringBuilder namePic = new StringBuilder("src/test/resources/pictures/")
                        .append(i).append(".jpg");
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
        product.setNameProduct("productNameTEST");
        product.setAmount(111);
        product.setPrice(111.11);
        product.setCategory(category);
        product.setDescription(descriptionMock);
        product.setPictures(picturesMock);
    }

    public static void updateFieldOfProduct(Product product) {
        product.setNameProduct("productNameTEST-UPDATE");
    }

    public static void initializeUser(User user, Product product) {

        //initialize product
        initializeProduct(product);
        new ProductDaoImpl().insert(product);

        //initialize shopping basket map
        Map<Product, Integer> shoppingBasket = new HashMap<>();
        shoppingBasket.put(product, 1111);

        //initialize wish list set
        Set<Product> wishList = new HashSet<>();
        wishList.add(product);

        //initialize user
        user.setFirstName("FirstNameTEST");
        user.setLastName("LastNameTEST");
        user.setPhone("PhoneTEST");
        user.setEmail("email@TEST");
        user.setAddress("AddressTEST");
        user.setLoginUser("LoginTEST");
        user.setPasswordUser("PasswordTEST");
        user.setWishList(wishList);
        user.setShoppingBasket(shoppingBasket);
    }

    public static void updateFieldsOfUser(User user) {
        user.setFirstName("FirstNameTEST-UPDATE");
        user.setLastName("LastNameTEST-UPDATE");
        user.setPhone("PhoneTEST-UPDATE");
        user.setEmail("email@TEST-UPDATE");
        user.setAddress("AddressTEST-UPDATE");
        user.setLoginUser("LoginTEST-UPDATE");
        user.setPasswordUser("PasswordTEST-UPDATE");
    }
}
