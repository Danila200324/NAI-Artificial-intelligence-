import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Compute {

    static double[] vector = null;

    public static String computeKNN(int k, String vec, String pathFile) {
        vector = Arrays.stream(vec.split(",")).mapToDouble(Double::parseDouble).toArray();
        List<Result> results = null;
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(new FileInputStream(pathFile)))) {
            results = bufferedReader.lines().map(x -> {
                String[] s = x.split(",");
                double[] s1 = Arrays.stream(Arrays.copyOf(s, s.length - 1)).mapToDouble(Double::parseDouble).toArray();
                return new Result(calculateDistance(s1), new Product(s1, s[s1.length]));
            }).sorted(Comparator.comparingDouble(Result::getDistance)).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert results != null;
        Map.Entry<String, Integer> frequency = results.subList(0, k).stream().
                collect(Collectors.toMap(x -> x.getProduct().getName(), x -> 1, Integer::sum))
                .entrySet().stream().max(Map.Entry.comparingByValue()).orElse(null);
        StringBuilder res = new StringBuilder();
        results.subList(0,k).forEach(x -> res.append(x).append("\n"));
        res.append("The frequency is ").append(frequency != null ? frequency.getKey() : null).append(".");
        return res.toString();
    }

    public static double calculateDistance(double[] array) {
        double result = 0;
        double[] newArray = null;
        if (vector.length < array.length) {
            newArray = Arrays.copyOf(vector, array.length);
            for (int i = 0; i < array.length; i++)
                result += Math.pow(newArray[i] - array[i], 2);
            return Math.sqrt(result);
        } else if (vector.length > array.length) {
            newArray = Arrays.copyOf(array, vector.length);
            for (int i = 0; i < vector.length; i++)
                result += Math.pow(vector[i] - newArray[i], 2);
            return Math.sqrt(result);
        } else {
            for (int i = 0; i < vector.length; i++)
                result += Math.pow(vector[i] - array[i], 2);
            return Math.sqrt(result);
        }
    }

}
