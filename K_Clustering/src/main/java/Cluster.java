import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cluster {

    private double[] center;

    private List<Entity> entities;

    public Cluster(double[] center) {
        this.center = center;
        entities = new ArrayList<>();
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public double[] getCenter() {
        return center;
    }

    public void setCenter(double[] center) {
        this.center = center;
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public void setEntities(List<Entity> entities) {
        this.entities = entities;
    }

    @Override
    public String toString() {
        return "Cluster{" +
                "center=" + Arrays.toString(center) +
                ", entities=" + entities +
                '}';
    }
}
