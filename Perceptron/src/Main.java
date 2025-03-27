import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String trainPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\Perceptron\\src\\train\\";
        Scanner scanner = new Scanner(System.in);

        System.out.println("Input the train set name:");
        String trainSetName = scanner.nextLine();

        trainPath+=trainSetName;


        String testPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\Perceptron\\src\\test\\";

        System.out.println("Input the test set name:");
        String testSetName = scanner.nextLine();

        testPath+=testSetName;


        List<Point> trainPoints =  DataLoader.load(trainPath);
        List<Point> testPoints =  DataLoader.load(testPath);


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