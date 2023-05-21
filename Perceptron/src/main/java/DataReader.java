import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {

    public static List<Entity> splitEntitiesFromTest(String fileTest) throws IOException {
        List<Entity> entities = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileTest)))) {
            entities = bufferedReader.lines().map(x -> {
                String[] s = x.split(",");
                double[] s1 = Arrays.stream(Arrays.copyOf(s, s.length - 1)).mapToDouble(Double::parseDouble).toArray();
                return new Entity(s1, s[s.length - 1]);
            }).collect(Collectors.toList());
        }
        return entities;
    }

    public static double[] getFromInput(String vector){
        return Arrays.stream(vector.split(",")).mapToDouble(Double::parseDouble).toArray();
    }

}
