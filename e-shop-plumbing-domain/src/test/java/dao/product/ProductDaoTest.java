package dao.product;

import dao.product.category.Category;
import dao.product.category.CategoryDaoImpl;
import org.junit.Test;

import static dao.DatabaseTools.clearDatabaseFromTestingRows;
import static dao.DatabaseTools.initializeProduct;
import static dao.DatabaseTools.updateFieldOfProduct;
import static org.junit.Assert.*;

public class ProductDaoTest {

    @Test
    public void insert_selectById_update_list_remove_with_database_product() {
        try {
            //given
            ProductDao productDao = new ProductDaoImpl();
            Product product = new Product();
            initializeProduct(product);

            //when
            productDao.insert(product);

            //then
            assertEquals(product.getNameProduct(), productDao.selectById(product.getIdProduct()).getNameProduct());
            assertTrue(productDao.list().stream().anyMatch(
                    products -> products.getIdProduct().equals(product.getIdProduct())));

            //when
            updateFieldOfProduct(product);
            productDao.update(product);

            //then
            assertEquals(product.getNameProduct(), productDao.selectById(product.getIdProduct()).getNameProduct());
            assertTrue(productDao.list().stream().anyMatch(
                    products -> products.getIdProduct().equals(product.getIdProduct())));

            //when
            productDao.remove(product);
            new CategoryDaoImpl().remove(product.getCategory());


            //then
            try {
                productDao.selectById(product.getIdProduct()).getIdProduct();
                fail();
            } catch (NullPointerException ignore) {
            }
        } catch (Exception e) {
            clearDatabaseFromTestingRows();
            fail("test not ended");
        }
    }

    @Test
    public void listByCategoryTest() {
        //given
        Category category = new CategoryDaoImpl().selectById(1);
        ProductDao productDao = new ProductDaoImpl();

        //when, then
        assertTrue(productDao.listByCategory(category).stream().anyMatch(
                products -> products.getCategory().equals(category)));
    }
}