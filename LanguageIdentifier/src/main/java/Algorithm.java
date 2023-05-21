import java.util.List;

public class Algorithm {
    private final double LEARNING_RATE = 0.01;
    private int NUMBER_OF_ITERATIONS = 1000;
    private double[][] weights;
    private double[] bias;
    private int numClasses;
    private String[] classes;
    private List<Entity> trainingEntities;
    private List<Entity> testEntities;
    private DataReader dataReader;

    public Algorithm(String trainingFile, String testFile) {
        dataReader = new DataReader();
        trainingEntities = dataReader.getAllEntitiesFromFile(trainingFile);
        testEntities = dataReader.getAllEntitiesFromFile(testFile);
        classes = dataReader.getNames().toArray(new String[0]);
        numClasses = classes.length;
    }

    public void train() {
        initializeWeights();
        for (int iteration = 0; iteration < NUMBER_OF_ITERATIONS; iteration++) {
            for (Entity entity : trainingEntities) {
                double[] net = new double[numClasses];
                for (int c = 0; c < numClasses; c++) {
                    net[c] = bias[c];
                    for (int i = 0; i < weights[c].length; i++) {
                        net[c] += entity.getCoordinates()[i] * weights[c][i];
                    }
                    if (net[c] > 0) {
                        if (!classes[c].equals(entity.getType())) {
                            updateWeights(entity.getCoordinates(), weights[c], LEARNING_RATE * -1);
                            updateBias(c, -LEARNING_RATE);
                            updateWeights(entity.getCoordinates(), weights[getClassIndex(entity.getType())], LEARNING_RATE);
                            updateBias(getClassIndex(entity.getType()), LEARNING_RATE);
                        }
                    } else {
                        if (classes[c].equals(entity.getType())) {
                            updateWeights(entity.getCoordinates(), weights[c], LEARNING_RATE);
                            updateBias(c, LEARNING_RATE);
                        }
                    }
                }
            }

        }
    }


    public void test(List<Entity> entities) {
        this.testEntities = entities;
        int correct = 0;
        for (Entity entity : testEntities) {
            int predicted = predict(entity);
            if (predicted == getClassIndex(entity.getType())) {
                correct++;
            }else{
                System.err.println("True language: " + entity.getType() +
                        ", predicted language: " + classes[predicted] + ";");
            }
        }
        double accuracy = (double) correct / testEntities.size();
        System.out.println("Test accuracy: " + accuracy);
    }

    private int predict(Entity entity) {
        double[] net = new double[numClasses];
        for (int c = 0; c < numClasses; c++) {
            net[c] = bias[c];
            for (int i = 0; i < weights[c].length; i++) {
                net[c] += entity.getCoordinates()[i] * weights[c][i];
            }
        }
        int maxIndex = 0;
        for (int c = 1; c < numClasses; c++) {
            if (net[c] > net[maxIndex]) {
                maxIndex = c;
            }
        }
        return maxIndex;
    }

    public void testWithText(String text){
        double[] net  = new double[numClasses];
        Entity entity = new Entity(dataReader.createVectorOfAlphabet(text), ""); // create an entity with an empty type
        for(int c = 0; c < numClasses; c++){
            net[c] = bias[c];
            for(int i = 0; i < weights[c].length; i++){
                net[c] += entity.getCoordinates()[i] * weights[c][i];
            }
        }
        int maxIndex = 0;
        for (int c = 1; c < numClasses; c++) {
            if (net[c] > net[maxIndex]) {
                maxIndex = c;
            }
        }
        String predictedClass = classes[maxIndex];
        System.out.println("Predicted class for text \"" + text + "\": " + predictedClass);
    }


    private int getClassIndex(String className) {
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].equals(className)) {
                return i;
            }
        }
        return -1;
    }

    public void initializeWeights() {
        int num = trainingEntities.get(0).getCoordinates().length;
        weights = new double[numClasses][num];
        bias = new double[numClasses];
        for (int c = 0; c < numClasses; c++) {
            for (int i = 0; i < num; i++) {
                weights[c][i] = Math.random();
            }
            bias[c] = Math.random();
        }
        normalizeVector();
    }

    private void normalizeVector(){
        for(int i = 0; i < weights.length; i++){
            double sum = 0;
            for(int a = 0; a < weights[i].length; a++){
                sum += Math.pow(weights[i][a], 2);
            }
            double magnitude = Math.sqrt(sum);
            double[] normalizedVector = new double[weights[i].length];
            for (int b = 0; b < normalizedVector.length; b++) {
                normalizedVector[i] = weights[i][b] / magnitude;
            }

            System.arraycopy(normalizedVector, 0, weights[i], 0, weights[i].length);
        }
    }



    private void updateWeights(double[] coordinates, double[] weights, double learningRate) {
        for (int i = 0; i < coordinates.length; i++) {
            weights[i] += learningRate * coordinates[i];
        }
    }

    private void updateBias(int classIndex, double learningRate) {
        bias[classIndex] += learningRate;
    }

    public List<Entity> getTrainingEntities() {
        return trainingEntities;
    }

    public List<Entity> getTestEntities() {
        return testEntities;
    }
}

