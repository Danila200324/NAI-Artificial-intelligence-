import java.util.Objects;

public class Result {
    private double distance;
    private Product product;

    public Result(){

    }

    public Result(double distance, Product product) {
        this.distance = distance;
        this.product = product;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Double.compare(result.distance, distance) == 0 && Objects.equals(product, result.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(distance, product);
    }

    @Override
    public String toString() {
        return "Result{" +
                "distance=" + distance +
                ", product=" + product +
                '}';
    }
}
