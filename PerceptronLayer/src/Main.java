import java.util.*;

public class Main {

    private static List<Point> trainPoints;
    private static List<Point> testPoints;

    private static List<String> trainLanguages;
    private static List<String> testLanguages;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        filesLoading(scanner);

        System.out.println("Pick a learning rate:");
        double learnRate = scanner.nextDouble();

        System.out.println("Enter a number of epochs:");
        int epochs = scanner.nextInt();

        PerceptronLayer layer = new PerceptronLayer(trainPoints.getFirst().getVector().length, learnRate, trainLanguages);
        layer.train(trainPoints, epochs);

        double trainAccuracy = layer.calculateAccuracy(trainPoints) * 100;
        double testAccuracy = layer.calculateAccuracy(trainPoints) * 100;

        System.out.println("Training accuracy: " + trainAccuracy + "%");
        System.out.println("Test accuracy: " + testAccuracy + "%");
    }

    private static void filesLoading(Scanner scanner){

        String trainPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\PerceptronLayer\\src\\train\\";

        System.out.println("Input the train set name:");
        String trainSetName = scanner.nextLine();

        trainPath+=trainSetName;


        String testPath = "C:\\Users\\Dimka\\IdeaProjects\\NAI\\PerceptronLayer\\src\\test\\";

        System.out.println("Input the test set name:");
        String testSetName = scanner.nextLine();

        testPath+=testSetName;


        trainPoints =  DataLoader.load(trainPath);
        testPoints =  DataLoader.load(testPath);



        System.out.println("Your train set:");
        trainLanguages = new ArrayList<>();
        for(Point point : trainPoints){
            System.out.println(point);

            trainLanguages.add(point.getName());
        }

        System.out.println("Your test set:");
        for(Point point : testPoints){
            System.out.println(point);
        }
    }
}

