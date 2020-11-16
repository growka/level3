package dev_lesson7;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class TestRunClass {
    public static void start(Class c){
        List<Method> methods = new ArrayList<>();
        Method[] classMethods = c.getDeclaredMethods();

        for (Method m : classMethods) {
            if (m.isAnnotationPresent(Test.class)) {
                methods.add(m);
            }
        }
        Collections.sort(methods, (o1, o2) -> -(o1.getAnnotation(Test.class).priority()- o2.getAnnotation(Test.class).priority()));

        for (Method m : classMethods) {
            if(m.isAnnotationPresent(BeforeSuite.class )) {
                if (methods.size() > 0 && methods.get(0).isAnnotationPresent(BeforeSuite.class)){
                    throw new RuntimeException("To many @BeforeSuite");
                }
                methods.add(0,m);
            }
        }

        for (Method m : classMethods) {
            if(m.isAnnotationPresent((AfterSuite.class))) {
                if (methods.size() > 0 && methods.get(methods.size()-1).isAnnotationPresent(AfterSuite.class)){
                    throw new RuntimeException("To many @AfterSuite");
                }
                methods.add(m);
            }
        }

        for (Method m : methods) {
            try {
                m.invoke(null);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
}
