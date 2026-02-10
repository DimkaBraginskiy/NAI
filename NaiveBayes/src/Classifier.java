import java.util.*;

public class Classifier {

    private Map<String, Integer> classCounts = new HashMap<>();

    // < feature index, < class name, < feature value, count of it >>>
    private Map<Integer, Map<String, Map<String, Integer>>> featureCounts = new HashMap<>();

    private Map<Integer, Set<String>> featureValueSets = new HashMap<>();


    //Smoothing constant:
    private static final double ALPHA = 1.0;


    public void train(List<Point> trainingPoints) {
        classCounts.clear();
        featureCounts.clear();
        featureValueSets.clear();

        // Initialize counts
        classCounts.put("e", 0);
        classCounts.put("p", 0);

        // Process each mushroom in training data
        for (Point point : trainingPoints) {
            String[] features = point.getVector(); // getting array of letters

            String mushroomClass = features[0]; // First element is the class

            // 1. Update class count by 1
            classCounts.put(mushroomClass, classCounts.get(mushroomClass) + 1);


            // 2. Process each feature (skip class at index 0)
            for (int i = 1; i < features.length; i++) {
                String featureValue = features[i];

                //initializing outer map and passing empty middle map. key - index of feature
                featureCounts.putIfAbsent(i, new HashMap<>());
                featureValueSets.putIfAbsent(i, new HashSet<>());


                featureValueSets.get(i).add(featureValue);

                //Putting values into middle map and adding empty map
                featureCounts.get(i).putIfAbsent(mushroomClass, new HashMap<>());


                //Inner empty map of featureCounts
                Map<String, Integer> valueCounts =
                        featureCounts.get(i).get(mushroomClass);

                //Updating value counts in inner map
                valueCounts.put(featureValue,
                        valueCounts.getOrDefault(featureValue, 0) + 1);
            }
        }

        System.out.println("\n=== FINAL TRAINING RESULTS ===");
        System.out.println("Class counts: " + classCounts);
        System.out.println("\nFeature counts:");
        for (int featureIndex : featureCounts.keySet()) {
            System.out.println("Feature " + featureIndex + ":");
            for (String cls : featureCounts.get(featureIndex).keySet()) {
                System.out.println("  " + cls + ": " + featureCounts.get(featureIndex).get(cls));
            }
        }
        System.out.println("\nAll feature values occurred in each iteration:");
        System.out.println(featureValueSets);
    }


    public String predict(Point point) {
        String[] features = point.getVector();
        System.out.println("\nPredicting for: " + Arrays.toString(features));

        String bestClass = null;
        double maxScore = Double.NEGATIVE_INFINITY;

        // Calculate score for each class ("e" and "p")
        for (String mushroomClass : classCounts.keySet()) {

            double score = Math.log(getClassProbability(mushroomClass)); //taking log if the values become too small
            System.out.println("Initial score for " + mushroomClass + ": " + score);

            // Multiply probabilities of all features (add logs to avoid underflow)
            for (int featureIndex = 1; featureIndex < features.length; featureIndex++) {
                String featureValue = features[featureIndex];
                double featureProb = getFeatureProbability(featureIndex, featureValue, mushroomClass);

                score += Math.log(featureProb);
                System.out.println("    Feature " + featureIndex + "=" + featureValue +
                        ": P=" + featureProb + " → New score: " + score);
            }

            // Track the best class
            if (score > maxScore) {
                maxScore = score;
                bestClass = mushroomClass;
            }
        }


        //performing final decision after going through each class:

        System.out.println("  Final prediction: " + bestClass + " (score: " + maxScore + ")");
        return bestClass;
    }


    private double getClassProbability(String mushroomClass) {
        // P = count of class * alpha / total count + alpha * number of occurrencies (AUTOMATIC SMOOTHING)
        return classCounts.getOrDefault(mushroomClass, 0) + ALPHA / totalSamples() + ALPHA * classCounts.size();
    }

    private double getFeatureProbability(int featureIndex, String featureValue, String mushroomClass) {
        //getting count of a feature of a class of an index....
        int count = featureCounts.getOrDefault(featureIndex, new HashMap<>())
                .getOrDefault(mushroomClass, new HashMap<>())
                .getOrDefault(featureValue, 0);

        int classTotal = classCounts.getOrDefault(mushroomClass, 0);

        //ocurrencies of features per iteration of index
        int numValues = featureValueSets.getOrDefault(featureIndex, new HashSet<>()).size();

        // P(feature|class) = (count + α) / (class_count + α*num_values)  (AUTOMATIC SMOOTHING)
        return (count + ALPHA) / (classTotal + ALPHA * numValues);
    }


    private int totalSamples() {
        //converting complex Integer type to an int and returning it:
        return classCounts.values().stream().mapToInt(Integer::intValue).sum();
    }


    public void evaluate(List<Point> testPoints) {
        int correct = 0;
        int TP = 0;
        int FP = 0;
        int TN = 0;
        int FN = 0;

        for (Point point : testPoints) {
            String[] features = point.getVector();
            String trueClass = features[0];

            //calling a method where all the logic is handled:
            String predictedClass = predict(new Point("test", features));

            if(predictedClass.equals(trueClass)) {
                correct++;
                if(predictedClass.equals("p")){
                    TP++;
                }
            }else{
                if(predictedClass.equals("p")){
                    FP++;
                }else{
                    FN++;
                }
            }
        }

        double accuracy = (double) correct / testPoints.size() * 100;

        double precision = (TP+FP > 0) ? (double) TP / (TP + FP) * 100 : 0;

        double recall = (TP+FN > 0) ? (double) TP / (TP + FN) * 100 : 0;

        double Fmeasure = (precision + recall > 0) ? (double) (2 * precision * recall) / (precision + recall) * 100 : 0;


        System.out.println("\nAccuracy: " + accuracy + "%");

        System.out.println("\nPrecision: " + precision + "%");
        System.out.println("\nRecall: " + recall + "%");
        System.out.println("\nF-Measure: " + Fmeasure + "%");


    }
}
