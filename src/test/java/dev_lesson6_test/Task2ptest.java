package dev_lesson6_test;

import dev_lesson6.Task2;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RunWith(value = Parameterized.class)
public class Task2ptest {
    private static Task2 t2 = null;

    private int[] in;
    private int[] out;
    private String testMessage;

    public Task2ptest(int[] in, int[] out, String testMessage){
        this.in = in;
        this.out = out;
        this.testMessage = testMessage;
    }
    @Before
    public void init() {
        System.out.println("T2 initialize:");
        t2 = new Task2();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> inData() {
        return Arrays.asList(new Object[][]{
           {new int[]{1,2,5,4,3,7,2}, new int[]{3,7,2}, "Первый тест"}, {new int[]{1,2,4,5,1}, new int[]{5,1}, "Второй тест"},
           {new int[]{4,4,4,4,2}, new int[]{2}, "Третий тест"}
        });
    }

    @Test
    public void task2pTest1() {
        System.out.println(testMessage);
        Assert.assertArrayEquals(out,t2.afterFour(in));
    }

    @AfterClass
    public static void endT2() {
        t2 = null;
        System.out.println();
    }

}
