public class Main {
    public static void main(String[] args) {
        double learningRate = 0.01;
        int epochs = 10;
        int dimension = 2;

        Perceptron perceptron = new Perceptron(learningRate, epochs, dimension);
    }
}