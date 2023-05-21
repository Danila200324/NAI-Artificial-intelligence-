import java.util.List;

public class Entity {

    private double[] coordinates;
    private String name;
    private byte type;

    public Entity(double[] coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
        addType(name);
    }

    private void addType(String name){
        switch (name){
            case "Iris-versicolor" -> type = 1;
            case "Iris-virginica" -> type = 0;
        }
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }
    public byte getType() {
        return type;
    }
}
