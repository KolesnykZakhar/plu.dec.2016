package dao.product.category;

import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryTest {
    private final static int TEST_VALUE_ID_FOR_FIRST_CATEGORY = 1;
    private final static int TEST_VALUE_ID_FOR_SECOND_CATEGORY = 2;

    @Test
    public void equalsTest_differentIdNotEquals() throws Exception {
        //given
        Category categoryFirst = new Category();
        Category categorySecond = new Category();

        //when
        categoryFirst.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);
        categorySecond.setIdCategory(TEST_VALUE_ID_FOR_SECOND_CATEGORY);

        //then
        assertFalse(categoryFirst.equals(categorySecond));
        assertFalse(categorySecond.equals(categoryFirst));
    }

    @Test
    public void equalsTest_sameId() throws Exception {
        //given
        Category categoryFirst = new Category();
        Category categorySecond = new Category();

        //when
        categoryFirst.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);
        categorySecond.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);

        //then
        assertTrue(categoryFirst.equals(categorySecond));
        assertTrue(categorySecond.equals(categoryFirst));
    }

    @Test
    public void equalsTest_differentClasses() throws Exception {
        //given
        Category categoryFirst = new Category();
        Object categorySecond = new Object();

        //when, then
        assertFalse(categoryFirst.equals(categorySecond));
        assertFalse(categorySecond.equals(categoryFirst));
    }

    @Test
    public void equalsTest_reflectivity() throws Exception {
        //given
        Category categoryFirst = new Category();

        //when, then
        assertTrue(categoryFirst.equals(categoryFirst));
    }

    @Test
    public void equalsTest_nullReference() throws Exception {
        //given
        Category categoryFirst = new Category();

        //when, then
        assertFalse(categoryFirst.equals(null));
    }

    @Test
    public void equalsTest_transitivity() throws Exception {
        //given
        Category categoryFirst = new Category();
        Category categorySecond = new Category();
        Category categoryThird = new Category();
        categoryFirst.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);
        categorySecond.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);
        categoryThird.setIdCategory(TEST_VALUE_ID_FOR_FIRST_CATEGORY);

        //when
        boolean fst = categoryFirst.equals(categorySecond);
        boolean snd = categoryFirst.equals(categoryThird);
        boolean thd = categorySecond.equals(categoryThird);

        //then
        assertTrue(fst & snd & thd);
    }
}