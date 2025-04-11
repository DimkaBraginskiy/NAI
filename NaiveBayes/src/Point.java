import java.util.Arrays;

public class Point {
    private String name;
    private String[] vector;

    public Point(String name, String[] vector) {
        this.name = name;
        this.vector = vector;
    }

    public String getName() {
        return name;
    }

    public String[] getVector() {
        return vector;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVector(String[] vector) {
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
