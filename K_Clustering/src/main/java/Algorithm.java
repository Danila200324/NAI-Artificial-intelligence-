import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

public class Algorithm {

    private String filePath;

    private int k;

    private List<Entity> entities;

    private List<Cluster> clusters = new ArrayList<>();


    private final int MAX_ITERATIONS = 1000;

    public Algorithm(String filePath, int k) {
        this.filePath = filePath;
        this.k = k;
        this.entities = DataReader.getAllEntities(filePath);
    }

    public List<Entity> peekRandomEntities() {
        Random random = new Random();
        List<Entity> pikedEntities = new ArrayList<>();
        int randomIndex = 0;
        for (int i = 0; i < k; i++) {
            randomIndex = random.nextInt(entities.size());
            pikedEntities.add(entities.get(randomIndex));
        }
        return pikedEntities;
    }

    public void calculate() {

        clusters = peekRandomEntities().stream().map(x -> new Cluster(x.getCoordinates())).collect(Collectors.toList());

//        clusters.add(new Cluster(entities.get(0).getCoordinates()));
//        clusters.add(new Cluster(entities.get(83).getCoordinates()));
//        clusters.add(new Cluster(entities.get(140).getCoordinates()));


        int count = 0;
        double distance = 0;
        while (count < MAX_ITERATIONS) {
            for (Entity entity : entities) {
                Map<Cluster, Double> map = new HashMap<>();
                for (Cluster cluster : clusters) {
                    map.put(cluster, calculateEuclideanDistance(entity, cluster));
                }
                Cluster minimumCluster = null;
                double minValue = Integer.MAX_VALUE;
                for (Map.Entry<Cluster, Double> entry : map.entrySet()) {
                    if (entry.getValue() < minValue) {
                        minValue = entry.getValue();
                        minimumCluster = entry.getKey();
                    }
                }
                minimumCluster.addEntity(entity);
            }

            calculateAverage();

            if (isEqual(distance(), distance)) {
                break;
            } else {
                distance = distance();
            }

            for (Cluster cluster : clusters) {
                cluster.getEntities().clear();
            }

            System.out.println(distance);

            count++;
        }
        System.out.println(count);
        show();
    }

    private void show() {
        for (Cluster cluster : clusters) {
            for (Entity entity : cluster.getEntities()) {
                System.out.println(entity.getName());
            }
            System.out.println("-------------------------------------------------------------");
        }

        int count = 0;
        for(Cluster cluster : clusters){
            System.out.println("The cluster " + ++count + " with number of values: " + cluster.getEntities().size());
            System.out.println("The classes: \n");
            for(Map.Entry<String, Double> entry : getMapOfEntitiesFrequencyInCluster(cluster).entrySet()){
                System.out.println(entry.getKey() + "\t" + entry.getValue());
            }
            System.out.println("The entropy: " + computeEntropy(getMapOfEntitiesFrequencyInCluster(cluster)));
        }
    }


    private Map<String, Double> getMapOfEntitiesFrequencyInCluster(Cluster cluster) {
        Map<String, Integer> countsByName = cluster.getEntities().stream()
                .collect(Collectors.groupingBy(Entity::getName, Collectors.reducing(0, e -> 1, Integer::sum)));


        Map<String, Double> frequencyByName = new HashMap<>();
        for (Map.Entry<String, Integer> entry : countsByName.entrySet()) {
            String key = entry.getKey();
            frequencyByName.put(key, (double) entry.getValue() / cluster.getEntities().size());
        }

        return frequencyByName;
    }

    private double computeEntropy(Map<String, Double> frequencyByName){
        double sum = 0;
        for (Map.Entry<String, Double> entry : frequencyByName.entrySet()) {
            sum += -entry.getValue() * Math.log(entry.getValue()) / Math.log(2);
        }
        return sum;
    }

    private boolean isEqual(double x, double y) {
        return Math.abs(x - y) < 0.000001;
    }


    private double calculateEuclideanDistance(Entity entity, Cluster cluster) {
        double distance = 0;
        for (int a = 0; a < entity.getCoordinates().length; a++) {
            distance = Math.pow(entity.getCoordinates()[a] - cluster.getCenter()[a], 2);
        }
        return Math.sqrt(distance);
    }

    private double distance() {
        double result = 0;
        for (Cluster cluster : clusters) {
            for (Entity entity : cluster.getEntities()) {
                result += calculateEuclideanDistance(entity, cluster);
            }
        }
        return result;
    }

    private void calculateAverage() {
        for (Cluster cluster : clusters) {
            double[] coordinates = new double[cluster.getCenter().length];
            for (int i = 0; i < cluster.getCenter().length; i++) {
                double sum = cluster.getCenter()[i];
                for (Entity entity : cluster.getEntities()) {
                    sum += entity.getCoordinates()[i];
                }
                coordinates[i] = sum / cluster.getEntities().size();
            }
            cluster.setCenter(coordinates);
        }
    }


}
