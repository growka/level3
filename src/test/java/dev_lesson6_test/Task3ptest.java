package dev_lesson6_test;

import dev_lesson6.Task3;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(value = Parameterized.class)
public class Task3ptest {
    private static Task3 t3 = null;

    private int[] in;
    private String testMessage;

    public Task3ptest(int[] in, String testMessage){
        this.in = in;
        this.testMessage = testMessage;
    }
    @Before
    public void init() {
        System.out.println("T2 initialize:");
        t3 = new Task3();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> inData() {
        return Arrays.asList(new Object[][]{
                {new int[]{1,4,1,4,4,1,4}, "Первый тест"}, {new int[]{4,2,4,5,1}, "Второй тест"},
                {new int[]{4,4,4,4,2}, "Третий тест"}
        });
    }

    @Test
    public void task2pTest1() {
        System.out.println(testMessage);
        Assert.assertTrue(t3.oneAndFour(in));
    }

    @AfterClass
    public static void endT2() {
        t3 = null;
        System.out.println();
    }

}
