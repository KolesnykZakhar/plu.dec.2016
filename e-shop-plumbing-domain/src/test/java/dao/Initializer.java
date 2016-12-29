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

public class Initializer {
    public static void initializeUser(User user, Category category, Product product) {

        //initialize product
        initializeProduct(product, category);

        //initialize shopping basket map
        Map<Product, Integer> shoppingBasket = new HashMap<>();
        shoppingBasket.put(product, 2222);

        //initialize wish list set
        Set<Product> wishList = new HashSet<>();
        wishList.add(product);


        user.setFirstName("1");
        user.setLastName("1");
        user.setPhone("1");
        user.setEmail("1@gmail.com");
        user.setAddress("1");
        user.setLoginUser("1");
        user.setPasswordUser("1");
        user.setWishList(wishList);
        user.setShoppingBasket(shoppingBasket);
        new UserDaoImpl().insert(user);
    }

    public static void initializeProduct(Product product, Category category) {

        //initialize map with description
        Map<String, String> descriptionMock = new HashMap<>();
        descriptionMock.put("Толщина", "2мм");
        descriptionMock.put("Максимальное давление", "50 bar");
        descriptionMock.put("Страна производитель", "Украина");

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
        initializeCategory(category);

        //initialize product
        product.setName("1");
        product.setAmount(100);
        product.setPrice(11.11);
        product.setCategory(category);
        product.setDescription(descriptionMock);
        product.setPictures(picturesMock);
        new ProductDaoImpl().insert(product);
    }

    public static void initializeCategory(Category category) {
        category.setName("Категория");
        new CategoryDaoImpl().insert(category);
    }
}
