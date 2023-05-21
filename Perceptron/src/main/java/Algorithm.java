import java.io.IOException;
import java.util.List;

public class Algorithm {
    private String fileTraining;
    private String fileTest;
    private double bias;
    private double weights[];
    private double learningRate;
    private int numberOfIterations;
    private List<Entity> trainingEntities;
    private List<Entity> testEntities;

    public Algorithm(String fileTraining, String fileTest, int numberOfIterations, double learningRate) throws IOException {
        this.learningRate = learningRate;
        this.fileTraining = fileTraining;
        this.fileTest = fileTest;
        this.numberOfIterations = numberOfIterations;
        trainingEntities = DataReader.splitEntitiesFromTest(fileTraining);
        testEntities = DataReader.splitEntitiesFromTest(fileTest);
        initializingWeights();
        train();
    }

    private void initializingWeights() {
        weights = new double[trainingEntities.get(0).getCoordinates().length];
        for (int i = 0; i < weights.length; i++) {
            weights[i] = Math.random();
        }
        bias = Math.random();
    }

    public void train() {
        int number = 0;
        while (number < numberOfIterations) {
            for (Entity entity : trainingEntities) {
                double net = -bias;
                for (int i = 0; i < entity.getCoordinates().length; i++) {
                    net += entity.getCoordinates()[i] * weights[i];
                }
                if (net >= 0) {
                    if (1 != entity.getType()) {
                        bias = bias - learningRate * (entity.getType() - 1);
                        for (int i = 0; i < weights.length; i++) {
                            weights[i] = weights[i] + learningRate
                                    * (entity.getType() - 1)
                                    * entity.getCoordinates()[i];
                        }
                    }
                } else {
                    if (0 != entity.getType()) {
                        bias = bias - learningRate * (entity.getType());
                        for (int i = 0; i < weights.length; i++) {
                            weights[i] = weights[i] + learningRate
                                    * (entity.getType())
                                    * entity.getCoordinates()[i];
                        }
                    }
                }
                number++;
            }
        }
        show();
    }

    public void show() {
        double error = 0;
        double total = 0;
        for (Entity entity : testEntities) {
            double result = -bias;
            for (int j = 0; j < entity.getCoordinates().length; j++) {
                result += entity.getCoordinates()[j] * weights[j];
            }
            if (result >= 0) {
                System.out.println("Found: Iris-versicolor, real: " + entity.getName());
                if (!"Iris-versicolor".equals(entity.getName())) error++;
            } else {
                System.out.println("Found: Iris-virginica, real: " + entity.getName());
                if (!"Iris-virginica".equals(entity.getName())) error++;
            }
            total++;
        }
        System.out.println("Error: " + error * 100 / total);
    }

    public void checkVector(String vector) {
        double result = -bias;
        for (int j = 0; j < weights.length; j++) {
            result += DataReader.getFromInput(vector)[j] * weights[j];
        }
        if (result >= 0) {
            System.out.println("Iris-versicolor");
        } else {
            System.out.println("Iris-virginica");
        }
    }

    public double getLearningRate() {
        return learningRate;
    }

    public void setLearningRate(double learningRate) {
        this.learningRate = learningRate;
    }

    public String getFileTraining() {
        return fileTraining;
    }

    public void setFileTraining(String fileTraining) {
        this.fileTraining = fileTraining;
    }

    public String getFileTest() {
        return fileTest;
    }

    public void setFileTest(String fileTest) {
        this.fileTest = fileTest;
    }

    public double getBias() {
        return bias;
    }

    public void setBias(double bias) {
        this.bias = bias;
    }

    public double[] getWeights() {
        return weights;
    }

    public void setWeights(double[] weights) {
        this.weights = weights;
    }

    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }

    public List<Entity> getTrainingEntities() {
        return trainingEntities;
    }

    public void setTrainingEntities(List<Entity> trainingEntities) {
        this.trainingEntities = trainingEntities;
    }

    public List<Entity> getTestEntities() {
        return testEntities;
    }

    public void setTestEntities(List<Entity> testEntities) {
        this.testEntities = testEntities;
    }
}
