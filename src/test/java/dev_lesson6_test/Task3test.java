package dev_lesson6_test;

import dev_lesson6.Task3;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Task3test {

    private static Task3 t3;

    @BeforeClass
    public static void init() {
        System.out.println("Init Task3...");
        t3 = new Task3();
    }

    @Test
    public void testArray(){
        System.out.println("Первый тест:");
        Assert.assertTrue(t3.oneAndFour(new int[]{1, 4, 4, 1, 4, 4, 4, 4, 1}));
    }

    @Test
    public void testArray2(){
        System.out.println("Второй тест:");
        //ssert.assertTrue(t3.oneAndFour(new int[]{5, 3, 4, 5, 4, 4, 4, 4, 5}));
        Assert.assertFalse(t3.oneAndFour(new int[]{4,2,4,5,0}));

    }


    @AfterClass
    public static void endT2() {
        t3 = null;
    }

}
