import java.util.Arrays;
import java.util.Objects;

public class Product {
    private double[] characteristics;
    private String name;

    public Product(double[] characteristics, String name) {
        this.characteristics = characteristics;
        this.name = name;
    }
    public Product(){

    }

    public double[] getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(double[] characteristics) {
        this.characteristics = characteristics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Arrays.equals(characteristics, product.characteristics) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name);
        result = 31 * result + Arrays.hashCode(characteristics);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "characteristics=" + Arrays.toString(characteristics) +
                ", name='" + name + '\'' +
                '}';
    }
}
