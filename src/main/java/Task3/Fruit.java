package Task3;

public abstract class Fruit {

    private float weight;
    private String name;

    public Fruit(String name, float weight) {
        this.name = name;
        this.weight = weight;
    }

    public float getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

}
