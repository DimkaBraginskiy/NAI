import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class KNNClassifier {
        private int k;
        private List<Point> points;

    public KNNClassifier(int k, List<Point> points) {
        this.k = k;
        this.points = points;
    }

    public List<Point> predict(double[] testVector){
        List<Point> kNNPoints = new ArrayList<Point>();



        //for loop which takes vectors from point list one by one and calculates distance
        for(Point point : points){
            double distance = euclidianDistance(testVector, point.getVector());
            point.setDistance(distance);
        }

        //sorting points by distance in ascending order
        points.sort(Comparator.comparingDouble(Point::getDistance));

        for(int i = 0; i < k; i++){
            kNNPoints.add(points.get(i));
        }

        return kNNPoints;
    }

    public String classify(List<Point> kNNPoints){
        Map<String, Integer> frequency = new HashMap<String , Integer>();
        int length = kNNPoints.size();

        for(Point point : kNNPoints){
            String className = point.getName();
            frequency.put(className, frequency.getOrDefault(className, 0) + 1);
        }

        String predictedClass = null;
        int maxCount = -1;

        for(Map.Entry<String, Integer> entry : frequency.entrySet()){
            String className = entry.getKey();
            int count = entry.getValue();

            if(count > maxCount){
                maxCount = count;
                predictedClass = className;
            }
        }

        return predictedClass;
    }



    private double euclidianDistance(double[] v1, double[] v2){
        double sum = 0.0;
        for(int i = 0; i < v1.length; i++){
            sum += Math.pow(v1[i] - v2[i], 2);
        }
        return Math.sqrt(sum);
    }

    private double manhattanDistance(double[] v1, double[] v2){
        double sum = 0.0;
        for(int i = 0; i < v1.length; i++){
            sum += v1[i] - v2[i];
        }
        return sum;
    }

    public void predict(){

    }
}
