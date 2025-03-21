import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String testFPath = "C:\\Users\\Dimka\\Documents\\Semester4\\tpo\\Lectures\\PerceptronLayer\\src\\test";

        System.out.println("Enter the test file name:");
        String fName = scanner.nextLine().trim();

        testFPath+=fName;

        List<Point> testSet = DataLoader.load(testFPath);


    }
}