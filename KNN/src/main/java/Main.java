import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please put the k value: ");
        int k = scanner.nextInt();
        System.out.println("Please put the arguments of vector: ");
        String arg = scanner.next();
        System.out.println(Compute.computeKNN(k, arg, "src/main/resources/wdbc.data"));
        //1,2,3,4,5,6,7
    }
}
