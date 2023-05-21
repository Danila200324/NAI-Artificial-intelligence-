import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataReader {


    public static List<Entity> getAllEntities(String filePath) {
        List<Entity> entities = null;
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            entities = bufferedReader.lines().map(x -> {
                String[] s = x.split(",");
                double[] coordinates = Arrays.stream(Arrays.copyOf(s, s.length - 1)).
                        mapToDouble(Double::parseDouble).toArray();
                String name = s[s.length - 1];
                return new Entity(coordinates, name);
            }).collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }
}
