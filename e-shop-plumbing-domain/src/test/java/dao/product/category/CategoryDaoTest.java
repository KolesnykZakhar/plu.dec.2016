package dao.product.category;

import org.hibernate.ObjectNotFoundException;
import org.junit.Test;

import static dao.Initializer.initializeCategory;
import static dao.Initializer.updateCategory;
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
                o -> ((Category) o).getIdCategory().equals(category.getIdCategory())));

        //when
        updateCategory(category);
        categoryDao.update(category);

        //then
        assertEquals(category.getName(), categoryDao.selectById(category.getIdCategory()).getName());
        assertTrue(categoryDao.list().stream().anyMatch(
                o -> ((Category) o).getIdCategory().equals(category.getIdCategory())));

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