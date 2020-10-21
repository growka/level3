import java.util.ArrayList;


public class Task2<T> {

    private T[] array;

    public Task2(T[] array) { this.array = array; }

    public ArrayList<T> asList() {
        ArrayList<T> arrayList = new ArrayList<T>();
        for (T value : array) {
            arrayList.add(value);
        }
        return arrayList;
    }

    public void printElements() {
        for (T value : array) {
            System.out.println(value + " ");
        }
    }
}
