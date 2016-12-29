package dao;

import dao.product.category.Category;
import dao.product.category.CategoryDaoImpl;
import dao.product.Product;
import dao.product.ProductDaoImpl;
import dao.user.User;
import dao.user.UserDaoImpl;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPOutputStream;

public class DatabaseTools {
    public static void main(String[] args) {

    }
    public static void clearDatabaseFromTestingRows(){

    }

    public static void initializeCategory(Category category) {
        category.setName("CategoryNameTEST");
    }

    public static void updateFieldOfCategory(Category category) {
        category.setName("CategoryNameTEST-UPDATE");
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
        product.setName("productNameTEST");
        product.setAmount(111);
        product.setPrice(111.11);
        product.setCategory(category);
        product.setDescription(descriptionMock);
        product.setPictures(picturesMock);
    }

    public static void updateFieldOfProduct(Product product) {
        product.setName("productNameTEST");
    }

    public static void initializeUser(User user/*, Category category*/, Product product) {

        //initialize product
        initializeProduct(product/*, category*/);
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
