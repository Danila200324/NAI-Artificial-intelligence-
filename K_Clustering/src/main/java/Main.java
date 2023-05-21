
public class Main {
    private static String classpath = "C:\\NAIProjects\\K_Clustering\\src\\main\\resources\\iris (1).data";
    public static void main(String[] args) {
        Algorithm algorithm = new Algorithm(classpath, 3);
        algorithm.calculate();

    }
}