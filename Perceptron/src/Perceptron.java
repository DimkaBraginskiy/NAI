public class Perceptron {
    private double learningRate;
    private int epochs;
    private double bias;
    private int dimension;
    private double[] weights;


    public Perceptron(double learningRate, int epochs, int dimension) {
        this.learningRate = learningRate;
        this.epochs = epochs;
        this.bias = Math.random();
        this.dimension = dimension;
        this.weights = new double[dimension];
        for(int i = 0; i < dimension; i++){
            weights[i] = Math.random();
        }

    }


    public void train(double[][] trainingSet, int[] labels){
        for(int i = 0; i < epochs; i++){
            for(int j = 0; j < dimension; j++){
                updateWeights(trainingSet[i], labels[j]);
            }
        }
    }

    public int predict(double[] testVector) {
        double net = 0.0;
        for (int i = 0; i < epochs; i++) {
            net += learningRate * testVector[i];
        }
        net -= bias;
        if (net > 0) {
            return 1;
        } else {
            return 0;
        }
    }

    public void updateWeights(double[] trainVector, int label){
        int prediction = predict(trainVector);
        int error = prediction - label;

        for(int i = 0; i < weights.length; i++){
            weights[i] += learningRate * error * trainVector[i];
        }
        bias += learningRate * error;
    }
}
