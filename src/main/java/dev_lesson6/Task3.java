package dev_lesson6;

//Task 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
//Если в нем нет хоть одной четверки или единицы, то метод вернет false;
//Написать набор тестов для этого метода (по 3-4 варианта входных данных).

public class Task3 {

    public static void main(String[] args) {

        Task3 t3 = new Task3();
        //System.out.println(t3.oneAndFour(new int[]{1, 4, 1, 4, 4}));
        System.out.println(t3.oneAndFour(new int[]{4,2,4,5,1}));
        //System.out.println(t3.oneAndFour(new int[]{1, 3, 4, 1, 4, 4, 4, 4, 1}));


    }

    public boolean oneAndFour(int[] arrIn) {
        boolean isOne = false;
        boolean isFour = false;
        for (int value : arrIn) {
            if (value == 1) {
                isOne = true;
            }
            if (value == 4) {
                isFour = true;
            }

        }
        return isFour && isOne;
    }
}
