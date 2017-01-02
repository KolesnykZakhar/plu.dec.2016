package dao.user;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private final static int TEST_VALUE_ID_FOR_FIRST_USER = 1;
    private final static int TEST_VALUE_ID_FOR_SECOND_USER = 2;

    @Test
    public void hashCodeTest() throws Exception {
        //given
        User userFirst = new User();

        //when
        userFirst.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);

        //then
        assertEquals(userFirst.hashCode(), TEST_VALUE_ID_FOR_FIRST_USER);
    }

    @Test
    public void equalsTest_differentIdNotEquals() throws Exception {
        //given
        User userFirst = new User();
        User userSecond = new User();

        //when
        userFirst.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);
        userSecond.setIdUser(TEST_VALUE_ID_FOR_SECOND_USER);

        //then
        assertFalse(userFirst.equals(userSecond));
        assertFalse(userSecond.equals(userFirst));
    }

    @Test
    public void equalsTest_sameId() throws Exception {
        //given
        User userFirst = new User();
        User userSecond = new User();

        //when
        userFirst.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);
        userSecond.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);

        //then
        assertTrue(userFirst.equals(userSecond));
        assertTrue(userSecond.equals(userFirst));
    }

    @Test
    public void equalsTest_differentClasses() throws Exception {
        //given
        User userFirst = new User();
        Object userSecond = new Object();

        //when, then
        assertFalse(userFirst.equals(userSecond));
        assertFalse(userSecond.equals(userFirst));
    }

    @Test
    public void equalsTest_reflectivity() throws Exception {
        //given
        User userFirst = new User();

        //when, then
        assertTrue(userFirst.equals(userFirst));
    }

    @Test
    public void equalsTest_nullReference() throws Exception {
        //given
        User userFirst = new User();

        //when, then
        assertFalse(userFirst.equals(null));
    }

    @Test
    public void equalsTest_transitivity() throws Exception {
        //given
        User userFirst = new User();
        User userSecond = new User();
        User userThird = new User();
        userFirst.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);
        userSecond.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);
        userThird.setIdUser(TEST_VALUE_ID_FOR_FIRST_USER);

        //when
        boolean fst = userFirst.equals(userSecond);
        boolean snd = userFirst.equals(userThird);
        boolean thd = userSecond.equals(userThird);

        //then
        assertTrue(fst & snd & thd);

    }

}