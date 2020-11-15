package dev_lesson6_test;

import dev_lesson6.Task2;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class Task2test {

    private static Task2 t2;

    @BeforeClass
    public static void init() {
        System.out.println("Init Task2...");
        t2 = new Task2();
    }

    @Test
    public void testArray(){
        Assert.assertArrayEquals(new int[]{6, 5, 7, 8},t2.afterFour(new int[]{1, 2, 3, 5, 4, 6, 5, 7, 8}));
    }

    @Test
    public void testArray2(){
        Assert.assertArrayEquals(new int[]{6, 5},t2.afterFour(new int[]{1, 2, -1, 5, 4, 6, 5}));
    }

    @Test
    public void testArray3(){
        Assert.assertArrayEquals(new int[]{0,1,2},t2.afterFour(new int[]{1, 2, 3, 5, 4, 0, 1, 2}));
    }

    @AfterClass
    public static void endT2() {
        t2 = null;
    }

}
