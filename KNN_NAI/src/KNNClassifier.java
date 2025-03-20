import java.util.*;
import java.util.stream.Collectors;

public class KNNClassifier {
        private int k;
        private List<Point> points;

    public KNNClassifier(int k, List<Point> points) {
        this.k = k;
        this.points = points;
    }


    public List<Point> getKClosestPoints(double[] testVector){
        return points.
                stream()
                .sorted(Comparator.comparingDouble(x -> euclideanDistance(testVector, x.getVector())))
                .limit(k)
                .toList();
    }

    public void classify(List<Point> kNNPoints){
        /*Map<String, Integer> frequency = new HashMap<String , Integer>();
        int length = kNNPoints.size();

        for(Point point : kNNPoints){
            String className = point.getName();
            frequency.put(className, frequency.getOrDefault(className, 0) + 1);
        }

        String predictedClass = null;
        int maxCount = -1;
*/

        for (Point p : kNNPoints){
            String classified = predict(p);
            System.out.println(p + " classified: " + classified);
            System.out.println(classified.equals(p.getName()));
        }
    }

    public void classify(Point p){
        String classified = predict(p);
        System.out.println(p + " classified: " + classified);
        System.out.println(classified.equals(p.getName()));
    }

    private String predict(Point point){
        long maxCount = 0;
        String predictedClass = "";

        Map<String, Long> frequency = getKClosestPoints(point.getVector()).
                stream().
                collect(Collectors.groupingBy(Point::getName,
                        Collectors.counting()));

        for(Map.Entry<String, Long> entry : frequency.entrySet()){
            String className = entry.getKey();
            long count = entry.getValue();

            if(count > maxCount){
                maxCount = count;
                predictedClass = className;
            }
        }
        return predictedClass;
    }



    private double euclideanDistance(double[] v1, double[] v2){
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

}
