import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static int numberOfIterations = 20000;
    private static double learningRate  = 0.01;
    private static String trainingFile = "C:\\Perceptron\\src\\main\\java\\perceptron.data";
    private static String testingFile = "C:\\Perceptron\\src\\main\\java\\perceptron.test.data";
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) throws IOException {

        Algorithm algorithm1 = new Algorithm(trainingFile,
                testingFile, numberOfIterations, learningRate);
        UI(algorithm1);

    }

    public static void UI(Algorithm algorithm) throws IOException {
        System.out.println("If you want to train the perceptron one more time with number of iterations " +
                numberOfIterations + ", choose 1");
        System.out.println("If you want to change number of iterations from " + numberOfIterations + ", choose 2");
        System.out.println("if you want to change the learning rate from " + algorithm.getLearningRate() + ", choose 3");
        System.out.println("If you want to input your vector to classify, choose 4");
        int input = scanner.nextInt();
        switch (input){
            case 1 -> {
                algorithm.train();
            }
            case 2-> {
                System.out.println("New number of iterations");
                numberOfIterations = scanner.nextInt();
                algorithm.setNumberOfIterations(numberOfIterations);
            }
            case 3-> {
                System.out.println("Put new learning rate");
                learningRate = scanner.nextDouble();
                algorithm.setLearningRate(learningRate);
            }
            case 4-> {
                System.out.println("Put the vector in the format: x1,x2,x3,x4");
                String s = scanner.next();
                algorithm.checkVector(s);
            }
        }
        UI(algorithm);
    }
}
