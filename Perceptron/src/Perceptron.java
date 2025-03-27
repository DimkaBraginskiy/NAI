import java.util.List;

public class Perceptron {

    public double netCalculator(double[] input, double[] weights, double bias) {
        if(input.length != weights.length) {
            System.out.println("Error: input length != weights.length");
        }

        double net = 0.0;
        for(int i = 0; i < input.length; i++) {
            net += input[i] * weights[i];
        }
        net -= bias;
        return bias;
    }

    public int activationFunction(double net){
        if(net >= 0){
            return 1;
        }else{
            return 0;
        }
    }
}
