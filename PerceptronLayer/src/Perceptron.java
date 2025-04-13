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

        for (int i = 0; i < weights.length; i++) {
            weights[i] = random.nextDouble() * 0.1;
        }

        this.bias = random.nextDouble();

        System.out.println("Generated weights: " + Arrays.toString(weights) +
                "\nGenerated bias: " + bias);
    }

    public double predict(double[] input) {
        if (input == null || input.length != weights.length) {
            System.out.println("Error: input length != weights.length");
        }

        double net = 0.0;

        for (int i = 0; i < weights.length; i++) {
            net += input[i] * weights[i];
        }

        net -= bias;

        double activation = sigmoidActivation(net);

        return activation;
    }

    public void updateWeights(double[] input, double expected, double predicted) {
        double error = expected - predicted;

        for (int i = 0; i < weights.length; i++) {
            weights[i] += learnRate * error * input[i];
        }

        System.out.println("Updated Weights: " + Arrays.toString(weights));
    }

    public double updateBias(double d, double y) {
        bias -= learnRate * (d - y) * y * (1 - y);
        return bias;
    }

    private double sigmoidActivation(double net) {
        return (1.0 / (1.0 + Math.exp(-net)));
    }
}
