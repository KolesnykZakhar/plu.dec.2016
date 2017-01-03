package dao.product.category;

import org.junit.Test;

import static dao.DatabaseToolsForTesting.initializeCategory;
import static dao.DatabaseToolsForTesting.updateFieldOfCategory;
import static org.junit.Assert.*;

public class CategoryDaoTest {
    @Test
    public void insert_selectById_update_list_remove_with_database_category() {
        //given
        Category category = new Category();
        CategoryDao categoryDao = new CategoryDaoImpl();
        initializeCategory(category);

        //when
        categoryDao.insert(category);

        //then
        assertEquals(category.getNameCategory(), categoryDao.selectById(category.getIdCategory()).getNameCategory());
        assertTrue(categoryDao.list().stream().anyMatch(
                categories -> categories.getIdCategory().equals(category.getIdCategory())));

        //when
        updateFieldOfCategory(category);
        categoryDao.update(category);

        //then
        assertEquals(category.getNameCategory(), categoryDao.selectById(category.getIdCategory()).getNameCategory());
        assertTrue(categoryDao.list().stream().anyMatch(
                categories -> categories.getIdCategory().equals(category.getIdCategory())));

        //when
        categoryDao.remove(category);

        //then
        try {
            categoryDao.selectById(category.getIdCategory()).getIdCategory();
            fail();
        } catch (NullPointerException ignore) {
        }
    }
}