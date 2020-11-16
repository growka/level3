package dev_lesson7;

import java.text.Annotation;

public class TestClass{

    @Test (priority = 2)
    public static void test0(){
        System.out.println("Test priority 2");
    }

    @BeforeSuite
    public static void beforeTest(){
        System.out.println("Before test message!");
    }

    @Test (priority = 10)
    public static void test1(){
        System.out.println("Test priority 10");
    }

    @Test(priority = 1)
    public static void test2(){
        System.out.println("Test priority 1");
    }

    @Test(priority = 4)
    public static void test3(){
        System.out.println("Test priority 4");
    }

    @Test
    public static void test4(){
        System.out.println("priority default (5)");
    }

    @AfterSuite
    public static void afterTest(){
        System.out.println("After test message!");
    }
}
