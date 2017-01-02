package dao.product;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {
    private final static int TEST_VALUE_ID_FOR_FIRST_PRODUCT = 1;
    private final static int TEST_VALUE_ID_FOR_SECOND_PRODUCT = 2;

    @Test
    public void hashCodeTest() throws Exception {
        //given
        Product productFirst = new Product();

        //when
        productFirst.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);

        //then
        assertEquals(productFirst.hashCode(), TEST_VALUE_ID_FOR_FIRST_PRODUCT);
    }

    @Test
    public void equalsTest_differentIdNotEquals() throws Exception {
        //given
        Product productFirst = new Product();
        Product productSecond = new Product();

        //when
        productFirst.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);
        productSecond.setIdProduct(TEST_VALUE_ID_FOR_SECOND_PRODUCT);

        //then
        assertFalse(productFirst.equals(productSecond));
        assertFalse(productSecond.equals(productFirst));
    }

    @Test
    public void equalsTest_sameId() throws Exception {
        //given
        Product productFirst = new Product();
        Product productSecond = new Product();

        //when
        productFirst.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);
        productSecond.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);

        //then
        assertTrue(productFirst.equals(productSecond));
        assertTrue(productSecond.equals(productFirst));
    }

    @Test
    public void equalsTest_differentClasses() throws Exception {
        //given
        Product productFirst = new Product();
        Object productSecond = new Object();

        //then
        assertFalse(productFirst.equals(productSecond));
        assertFalse(productSecond.equals(productFirst));
    }

    @Test
    public void equalsTest_reflectivity() throws Exception {
        //given
        Product productFirst = new Product();

        //then
        assertTrue(productFirst.equals(productFirst));
    }

    @Test
    public void equalsTest_nullReference() throws Exception {
        //given
        Product productFirst = new Product();

        //then
        assertFalse(productFirst.equals(null));
    }

    @Test
    public void equalsTest_transitivity() throws Exception {
        //given
        Product productFirst = new Product();
        Product productSecond = new Product();
        Product productThird = new Product();
        productFirst.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);
        productSecond.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);
        productThird.setIdProduct(TEST_VALUE_ID_FOR_FIRST_PRODUCT);

        //when
        boolean fst = productFirst.equals(productSecond);
        boolean snd = productFirst.equals(productThird);
        boolean thd = productSecond.equals(productThird);

        //then
        assertTrue(fst & snd & thd);

    }

}