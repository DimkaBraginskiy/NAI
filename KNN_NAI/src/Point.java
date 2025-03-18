public class Point {
    private double[] vector;
    private String name;
    private double distance;

    public Point(double[] vector, String name) {
        this.vector = vector;
        this.name = name;
    }

    public void setVector(double[] vector) {
        this.vector = vector;
    }

    public double[] getVector(){
        return vector;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getDistance(){
        return distance;
    }

    @Override
    public String toString(){
        String res = "[";
        for(int i = 0; i < vector.length; i++){
            res += vector[i];
            res = i < vector.length-1 ? res+= ",": res+"]";
        }
        res += " " + name;
        return res;
    }
}
