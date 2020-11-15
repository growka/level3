package dev_lesson6;

public class Task2 {

//        Task 2. Написать метод, которому в качестве аргумента передается не пустой одномерный целочисленный массив.
//        Метод должен вернуть новый массив, который получен путем вытаскивания из исходного массива элементов, идущих после последней четверки.
//        Входной массив должен содержать хотя бы одну четверку, иначе в методе необходимо выбросить RuntimeException.
//        Написать набор тестов для этого метода (по 3-4 варианта входных данных). Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
public static void main(String[] args) {
    Task2 t = new Task2();
    //t.afterFour(new int[]{1, 2, 3, 5, 5, 6, 5, 7, 8});
}
    public int[] afterFour(int[] arr) {
        int ind = -1;
        //int[] arr = {1,2,3,4,5,6,4,7,8};
        try {
            for (int i = arr.length - 1; i > 0; i--) {
                if (arr[i] == 4) {
                    ind = i + 1;
                    break;
                }
            }
            if (ind == -1) {
                throw new RuntimeException();
            } else {
                int[] arr2 = new int[arr.length-ind];
                int in = 0;
                while (ind<arr.length) {
                    arr2[in] = arr[ind];
                    System.out.print(arr2[in]);
                    in++;
                    ind++;
                }
                return arr2;
            }
        } catch (RuntimeException e) {
            System.out.println("Нет 4 в массиве!");
        }
        return null;
    }
}
