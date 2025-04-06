import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Point> trainPoints;
    private static List<Point> testPoints;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        filesLoading(scanner);

        System.out.println("Pick a learning rate:");
        double learnRate = scanner.nextDouble();

        System.out.print("Enter number of epochs: ");
        int epochs = scanner.nextInt();

        Perceptron perceptron = new Perceptron(trainPoints.getFirst().getVector().length, learnRate);
        perceptron.train(trainPoints, epochs);

        System.out.println("Train Accuracy: " + (perceptron.calculateAccuracy(trainPoints) * 100) + "%");
        System.out.println("Test Accuracy: " + (perceptron.calculateAccuracy(testPoints) * 100) + "%");
    }

    private static void filesLoading(Scanner scanner){
        String trainPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\Perceptron\\src\\train\\";

        System.out.println("Input the train set name:");
        String trainSetName = scanner.nextLine();

        trainPath+=trainSetName;


        String testPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\Perceptron\\src\\test\\";

        System.out.println("Input the test set name:");
        String testSetName = scanner.nextLine();

        testPath+=testSetName;


        trainPoints =  DataLoader.load(trainPath);
        testPoints =  DataLoader.load(testPath);


        System.out.println("Your train set:");
        for(Point point : trainPoints){
            System.out.println(point);
        }

        System.out.println("Your test set:");
        for(Point point : testPoints){
            System.out.println(point);
        }
    }
}