package dao;

import dao.product.category.Category;
import dao.product.category.CategoryDao;
import dao.product.category.CategoryDaoImpl;
import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import static dao.DatabaseTools.initializeCategory;
import static dao.DatabaseTools.updateFieldOfCategory;
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
        assertEquals(category.getName(), categoryDao.selectById(category.getIdCategory()).getName());
        assertTrue(categoryDao.list().stream().anyMatch(
                categories -> categories.getIdCategory().equals(category.getIdCategory())));

        //when
        updateFieldOfCategory(category);
        categoryDao.update(category);

        //then
        assertEquals(category.getName(), categoryDao.selectById(category.getIdCategory()).getName());
        assertTrue(categoryDao.list().stream().anyMatch(
                categories -> categories.getIdCategory().equals(category.getIdCategory())));

        //when
        categoryDao.remove(category);

        //then
        try {
            categoryDao.selectById(category.getIdCategory()).getIdCategory();
            fail();
        } catch (ObjectNotFoundException ignore) {
        }
    }
}