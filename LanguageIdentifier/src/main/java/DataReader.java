import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataReader {
    private Set<String> names = new HashSet<>();

    public  List<Entity> getAllEntitiesFromFile(String filePath){
        List<Entity> entities = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))) {
            entities = bufferedReader.lines().map(x -> {
                String[] line = x.split(",", 2);
                names.add(line[0]);
                return new Entity(createVectorOfAlphabet(line[1]),line[0]);
            }).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }


    public double[] createVectorOfAlphabet(String text){
        int[] vector = new int[26];
        String textLatin = text.replaceAll("[^a-zA-Z]", "");
        for (char c : textLatin.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                vector[c - 'a']++;
            }
        }
        return normalize(vector);
    }

    public double[] normalize(int[] vector) {
        double sum = 0.0;
        for (double x : vector) {
            sum += Math.pow(x, 2);
        }
        double magnitude = Math.sqrt(sum);
        double[] normalizedVector = new double[vector.length];
        for (int i = 0; i < vector.length; i++) {
            normalizedVector[i] = vector[i] / magnitude;
        }
        return normalizedVector;
    }

    public Set<String> getNames() {
        return names;
    }

    public void setNames(Set<String> names) {
        this.names = names;
    }
}
