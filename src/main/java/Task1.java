public class Task1<T> {

    private T[] array;
    private T tmp;

    public Task1(T[] array) { this.array = array; }

    public void changeElements(int a, int b) {

        try {
            tmp = array[a];
            array[a] = array[b];
            array[b] = tmp;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Заданные значения: " + a + ", " + b + " вне границ массива.");
        }
    }

    public void printElements() {
        for (T value : array) {
            System.out.println(value + " ");
        }
    }
}


