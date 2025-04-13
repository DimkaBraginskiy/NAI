import java.util.*;

public class Main {

    private static List<Point> trainPoints;
    private static List<Point> testPoints;
    static Map<String, Integer> occurences = new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        filesLoading(scanner);

        System.out.println("Counting occurrences of each letter (train set):");

        vectorize(trainPoints);

        for (Map.Entry<String, Integer> entry : occurences.entrySet()) {
            System.out.println("Value: " + entry.getKey() + " -> Count: " + entry.getValue());
        }

    }

    private static void filesLoading(Scanner scanner) {
        String trainPath = "C:\\Users\\s30428\\Documents\\NAI\\NaiveBayes\\src\\";

        System.out.println("Input the train set name:");
        String trainSetName = scanner.nextLine();

        trainPath += trainSetName;


        String testPath = "C:\\Users\\s30428\\Documents\\NAI\\NaiveBayes\\src\\";

        System.out.println("Input the test set name:");
        String testSetName = scanner.nextLine();

        testPath += testSetName;


        trainPoints = DataLoader.load(trainPath);
        testPoints = DataLoader.load(testPath);


        System.out.println("Your train set:");
        for (Point point : trainPoints) {
            System.out.println(point);
        }

        System.out.println("Your test set:");
        for (Point point : testPoints) {
            System.out.println(point);
        }
    }

    private static void vectorize(List<Point> points) {
        for (Point p : points){

            String[] vector = p.getVector();


            for (String value: vector){
                occurences.put(value, occurences.getOrDefault(value, 0) + 1);
            }
        }
    }
}