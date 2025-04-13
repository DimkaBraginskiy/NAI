import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Perceptron {

    private double[] weights;
    private double bias;
    private double learnRate;

    private int epochs;
    private double errorThreshold;

    private Random random = new Random();

    public Perceptron(int inputSize, double learnRate) {
        this.weights = new double[inputSize];
        this.learnRate = learnRate;

        for(int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble() * 0.1;
        }

        this.bias = random.nextDouble();

        System.out.println("Generated weights: " + Arrays.toString(weights) +
                "\nGenerated bias: " + bias);
    }



    public int encodeLabel(String label){
        return label.equals("German") ? 0 : 1;
    }

    public int predict(double[] input) { // Activation function
        if(input == null || input.length != weights.length) {
            System.out.println("Error: input length != weights.length");
        }

        double net = bias;

        for(int i = 0; i < weights.length; i++) {
            net += input[i] * weights[i];
        }


        int af = (net >= 0) ? 1 : 0;
        //System.out.println("net: " + net + ". Activation function: " + af);

        return (net >= 0) ? 1 : 0; // Activation function
    }

    public void updateWeights(double[] input, int expected, int predicted){
        double error = expected - predicted;

        for(int i = 0; i < weights.length; i++){
            weights[i] += learnRate * error * input[i];
        }

        bias -= learnRate*error;

        System.out.println("Updated Weights: " + Arrays.toString(weights));
    }

    public double calculateAccuracy(List<Point> points){
        int correct = 0;
        int incorrect = 0;

        for(Point point : points){
            int predicted = predict(point.getVector());
            int actual = encodeLabel(point.getName());

            if(predicted == actual){
                correct++;
                System.out.println("Correct Prediction:\nPrediсted: " + predicted + " = Actual: " + actual);
            }else{
                System.out.println("\n!Incorrect Prediction:\nPrediсted: " + predicted + " != Actual: " + actual +"!\n");
            }
        }

        return (double) correct / points.size(); // returning accuracy
    }
}
