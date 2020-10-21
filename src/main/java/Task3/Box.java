package Task3;

import java.util.ArrayList;


public class Box<T extends Fruit> {

    private String type;
    private float boxWeight;

    private ArrayList<T> boxContents = new ArrayList<T>();

    public Box() {
        this.type = null;
    }

    public float getWeight() {

        boxWeight = 0.0f;
        for (T value : boxContents) {
            boxWeight += value.getWeight();
        }
        return boxWeight;
    }

    public void addFruit(T fruit) {
        boxContents.add(fruit);
    }

    public void addSomeFruit(T fruit, int count) {
        for (int i = 0; i < count; i++) {
            boxContents.add(fruit);
        }
    }

    public void addBox(Box<T> wastingBox) {

//        for (T value : wastingBox.boxContents) {
//            boxContents.add(value);
//        }
        boxContents.addAll(wastingBox.boxContents);
        wastingBox.boxContents.clear();
    }

    public boolean compare (Box<?> compareBox) {
        return compareBox.getWeight() == getWeight();
    }
}
