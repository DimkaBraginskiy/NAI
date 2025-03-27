import java.util.Arrays;

public class Point {
    private double[] vector;
    private String name;

    public Point(double[] vector, String name) {
        this.vector = vector;
        this.name = name;
    }

    public double[] getVector() {
        return vector;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Point{" +
                "vector=" + Arrays.toString(vector) +
                ", name='" + name + '\'' +
                '}';
    }
}
