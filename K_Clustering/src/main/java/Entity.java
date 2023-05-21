import java.util.Arrays;

public class Entity {

    private double[] coordinates;

    private String name;

    public Entity(double[] coordinates, String name) {
        this.coordinates = coordinates;
        this.name = name;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Entity{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", name='" + name + '\'' +
                '}';
    }
}
