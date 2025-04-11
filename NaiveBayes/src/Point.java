import java.util.Arrays;

public class Point {
    private String name;
    private String[] vector;

    public Point(String name, String[] vector) {
        this.name = name;
        this.vector = vector;
    }

    @Override
    public String toString() {
        return "Point{" +
                "name='" + name + '\'' +
                ", vector=" + Arrays.toString(vector) +
                '}';
    }
}
