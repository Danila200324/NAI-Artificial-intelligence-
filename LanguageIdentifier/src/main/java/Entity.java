import java.util.Arrays;

public class Entity {

    private double[] coordinates;
    private String type;

    public Entity(double[] coordinates, String name) {
        this.coordinates = coordinates;
        this.type = name;
    }

    public Entity() {
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }



    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", name='" + type + '\'' +
                ", type=" + type +
                '}';
    }
}
