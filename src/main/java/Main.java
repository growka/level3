import Task3.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        //Integer[] array = {1,2,3,4,5};
        String[] array = {"Привет", "как", "дела", "друг"};

        // Задание 1.
        System.out.println("Задание 1:");

        Task1 task1 = new Task1(array);
        task1.changeElements(2, 3);
        task1.printElements();

        // Задание 2.
        System.out.println("Задание 2:");

        Task2 task2 = new Task2(array);
        ArrayList arrayList = task2.asList();
        System.out.println(arrayList);

        // Задание 3.
        System.out.println("Задание 3:");

        Box<Apple> appleBox1 = new Box<Apple>(); //создаем две коробки для яблок
        Box<Apple> appleBox2 = new Box<Apple>();

        Box<Orange> orangeBox1 = new Box<Orange>(); //создаем две коробки для апельсинов
        Box<Orange> orangeBox2 = new Box<Orange>();

        appleBox1.addSomeFruit(new Apple(), 11); //добавляем яблоки разными методами
        appleBox2.addFruit(new Apple());

        System.out.println("Вес до пересыпания первой коробки яблок: " + appleBox1.getWeight() + " второй коробки: " + appleBox2.getWeight()); // проверка перед пересыпанием

        appleBox2.addBox(appleBox1);

        System.out.println("Вес после первой коробки яблок: " + appleBox1.getWeight() + " второй коробки: " + appleBox2.getWeight()); // проверка после пересыпания

        orangeBox1.addFruit(new Orange());              //добавляем апельсины разными методами
        orangeBox1.addSomeFruit(new Orange(), 7);

        orangeBox2.addBox(orangeBox1);      //пересыпаем апельсины

        System.out.println("Сравнение:");

        orangeBox1.addFruit(new Orange());

        System.out.println("Вес первой коробки яблок: " + appleBox1.getWeight() + " второй коробки: " + appleBox2.getWeight());

        System.out.println("Вес первой коробки апельсинов: " + orangeBox1.getWeight() + " второй коробки: " + orangeBox2.getWeight());

        System.out.println("Сравнение первой коробки апельсинов с первой коробкой яблок:" + appleBox1.compare(orangeBox1));

        System.out.println("Сравнение второй коробки апельсинов со второй коробкой яблок:" + appleBox2.compare(orangeBox2));


    }
}
