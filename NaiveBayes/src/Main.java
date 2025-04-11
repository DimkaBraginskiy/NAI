import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Point> trainPoints;
    private static List<Point> testPoints;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        filesLoading(scanner);

    }

    private static void filesLoading(Scanner scanner) {
        String trainPath = "C:\\Users\\Dimka\\IdeaProjects\\NaiveBayes\\src\\";

        System.out.println("Input the train set name:");
        String trainSetName = scanner.nextLine();

        trainPath += trainSetName;


        String testPath = "C:\\Users\\Dimka\\IdeaProjects\\NaiveBayes\\src\\";

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
}