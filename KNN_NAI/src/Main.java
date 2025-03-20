import javax.xml.crypto.Data;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String trainPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\KNN_NAI\\src\\train\\";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the test set name:");
        String testFname = scanner.nextLine();

        trainPath+=testFname;

        List<Point> trainSet = DataLoader.load(trainPath);

        System.out.println("Your train set:");
        for (Point point : trainSet) {
            System.out.println(point);
        }


        while(true){
            System.out.println("Provide an answer:\n" +
                    "1 -> Find k-Points to an Input-Vector\n" +
                    "2 -> Find k-Points for a test set of Vectors");
            int selection = scanner.nextInt();
            if(selection == 1){
                firstOption(scanner, trainSet);
                break;
            }else if(selection == 2){
                secondOption(scanner, trainSet);
                break;
            }
            else{
                System.out.println("Wrong input.\n");
            }
        }
    }

    private static void firstOption(Scanner scanner, List<Point> trainSet){
        System.out.println("Input the vector: val 1, val 2, ... , val n :(n depends on a train dataset dimension you provide)");
        String input = scanner.next().trim();

        System.out.println("Input the class name:");
        String className = scanner.next().trim();

        System.out.println("Input the value for k:");
        int k = scanner.nextInt();

        String[] parts = input.split(",");

        double[] vector = new double[parts.length];

        for(int i = 0; i < parts.length; i++){
            vector[i] = Double.parseDouble(parts[i]);
        }

        Point point = new Point(vector, className);

        KNNClassifier classifier = new KNNClassifier(k, trainSet);

        classifier.classify(point);
    }



    private static void secondOption(Scanner scanner, List<Point> trainSet){
        String testPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\KNN_NAI\\src\\test\\";

        System.out.println("Input the test set name");
        String fname = scanner.next().trim();

        System.out.println("Input the value for k:");
        int k = scanner.nextInt();

        testPath+=fname;

        List<Point> testSet = DataLoader.load(testPath);

        System.out.println("Your test set:");
        for(Point point : testSet) {
            System.out.println(point);
        }

        KNNClassifier classifier = new KNNClassifier(k, trainSet);

        classifier.classify(testSet);
    }
}
