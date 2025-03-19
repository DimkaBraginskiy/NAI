import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

//        double[] inputVector = {5.0, 3.3, 1.4, 0.2};
//
//
//
//
//        Map<String, double[]> vectors = new HashMap<>();
//
//        for (double[] vector : vectors) {
//            double distance = calculateDistance(inputVector, vector);
//            System.out.println(distance);
//        }
        String trainFname = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\KNN_NAI\\src\\iris.data";
        String testFname = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\KNN_NAI\\src\\iris.test.data";

        List<Point> trainSet = DataLoader.load(trainFname);
        List<Point> testSet = DataLoader.load(testFname);

        for (Point point : trainSet) {
            System.out.println(point);
        }

        System.out.println();

        for(Point point : testSet) {
            System.out.println(point);
        }
        System.out.println();

        int k = 3;
        KNNClassifier classifier = new KNNClassifier(k, trainSet);

        classifier.classify(testSet);


    }
}
