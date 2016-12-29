package dao;

import dao.product.Product;
import dao.product.ProductDao;
import dao.product.ProductDaoImpl;
import dao.product.category.CategoryDaoImpl;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import static dao.DatabaseTools.initializeProduct;
import static dao.DatabaseTools.updateFieldOfProduct;
import static org.junit.Assert.*;

public class ProductDaoTest {

    @Test
    public void insert_selectById_update_list_remove_with_database_product() {
        //given
        ProductDao productDao = new ProductDaoImpl();
        Product product = new Product();
        initializeProduct(product);

        //when
        productDao.insert(product);

        //then
        assertEquals(product.getName(), productDao.selectById(product.getIdProduct()).getName());
        assertTrue(productDao.list().stream().anyMatch(
                products -> products.getIdProduct().equals(product.getIdProduct())));

        //when
        updateFieldOfProduct(product);
        productDao.update(product);

        //then
        assertEquals(product.getName(), productDao.selectById(product.getIdProduct()).getName());
        assertTrue(productDao.list().stream().anyMatch(
                products -> products.getIdProduct().equals(product.getIdProduct())));

        //when
        productDao.remove(product);
        new CategoryDaoImpl().remove(product.getCategory());


        //then
        try {
            productDao.selectById(product.getIdProduct()).getIdProduct();
            fail();
        } catch (ObjectNotFoundException ignore) {
        }
    }
}