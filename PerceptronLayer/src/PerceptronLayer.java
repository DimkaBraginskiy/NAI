import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PerceptronLayer {


    private Map<String, Perceptron> perceptrons;
    private List<String> languages;
    private final int inputSize;
    private final double learnRate;

    public PerceptronLayer(int inputSize, double learnRate, List<String> languages) {
        this.perceptrons = new HashMap<>();
        this.languages = new ArrayList<>(languages);
        this.inputSize = inputSize;
        this.learnRate = learnRate;

        initPerceptrons();
    }



    private void initPerceptrons() {
        for(String language : languages) {
            Perceptron perceptron = new Perceptron(inputSize, learnRate);
            perceptrons.put(language, perceptron);
        }
    }

    public void train(List<Point> trainSet, int epochs){

        for(int epoch = 0; epoch < epochs; epoch++) {

            int totalErrors = 0;

            for(Map.Entry<String, Perceptron> entry : perceptrons.entrySet()) {
                String language = entry.getKey();
                Perceptron p = entry.getValue();
                int langErrors = 0;

                //Training each Perceptron fully:
                for (Point point : trainSet) {
                    String trueLang = point.getName();
                    double[] x = point.getVector();

                    int expected = language.equals(trueLang) ? 1 : 0;
                    int predicted = p.predict(point.getVector());

                    if(predicted != expected) {
                        p.updateWeights(x, expected, predicted);
                        langErrors++;
                    }
                }

                double accuracy = p.calculateAccuracy(trainSet);

                totalErrors += langErrors;
                System.out.println("Language errors: " + langErrors);
            }

            System.out.println("Total errors: " + totalErrors);

            if(totalErrors == 0) {
                System.out.println("Converged at epoch: " + epoch);
                break;
            }
        }
    }

    public double calculateAccuracy(List<Point> testData) {
        int correct = 0;
        for (Point point : testData) {
            if (predictLanguage(point.getVector()).equals(point.getName())) {
                correct++;
            }
        }
        return (double)correct / testData.size();
    }


    public String predictLanguage(double[] features) {
        String bestLanguage = null;
        double maxActivation = Double.NEGATIVE_INFINITY;

        for (Map.Entry<String, Perceptron> entry : perceptrons.entrySet()) {
            double activation = entry.getValue().predict(features);
            if (activation > maxActivation) {
                maxActivation = activation;
                bestLanguage = entry.getKey();
            }
        }
        return bestLanguage;
    }
}
