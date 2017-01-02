package dao.order;

import org.junit.Test;

import static org.junit.Assert.*;


public class OrderTest {
    private final static int TEST_VALUE_ID_FOR_FIRST_ORDER = 1;
    private final static int TEST_VALUE_ID_FOR_SECOND_ORDER = 2;

    @Test
    public void equalsTest_differentIdNotEquals() throws Exception {
        //given
        Order orderFirst = new Order();
        Order orderSecond = new Order();

        //when
        orderFirst.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);
        orderSecond.setIdOrder(TEST_VALUE_ID_FOR_SECOND_ORDER);

        //then
        assertFalse(orderFirst.equals(orderSecond));
        assertFalse(orderSecond.equals(orderFirst));
    }

    @Test
    public void equalsTest_sameId() throws Exception {
        //given
        Order orderFirst = new Order();
        Order orderSecond = new Order();

        //when
        orderFirst.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);
        orderSecond.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);

        //then
        assertTrue(orderFirst.equals(orderSecond));
        assertTrue(orderSecond.equals(orderFirst));
    }

    @Test
    public void equalsTest_differentClasses() throws Exception {
        //given
        Order orderFirst = new Order();
        Object orderSecond = new Object();

        //when, then
        assertFalse(orderFirst.equals(orderSecond));
        assertFalse(orderSecond.equals(orderFirst));
    }

    @Test
    public void equalsTest_reflectivity() throws Exception {
        //given
        Order orderFirst = new Order();

        //when, then
        assertTrue(orderFirst.equals(orderFirst));
    }

    @Test
    public void equalsTest_nullReference() throws Exception {
        //given
        Order orderFirst = new Order();

        //when, then
        assertFalse(orderFirst.equals(null));
    }

    @Test
    public void equalsTest_transitivity() throws Exception {
        //given
        Order orderFirst = new Order();
        Order orderSecond = new Order();
        Order orderThird = new Order();
        orderFirst.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);
        orderSecond.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);
        orderThird.setIdOrder(TEST_VALUE_ID_FOR_FIRST_ORDER);

        //when
        boolean fst = orderFirst.equals(orderSecond);
        boolean snd = orderFirst.equals(orderThird);
        boolean thd = orderSecond.equals(orderThird);

        //then
        assertTrue(fst & snd & thd);

    }

}