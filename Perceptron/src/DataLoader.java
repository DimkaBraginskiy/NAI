import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataLoader {
    public static double[][] loadFeatures(String filename) throws IOException {
        ArrayList<double[]> dataList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            double[] features = new double[parts.length - 1];

            for (int i = 0; i < parts.length - 1; i++) {
                features[i] = Double.parseDouble(parts[i]); // Convert to numbers
            }
            dataList.add(features);
        }
        br.close();

        return dataList.toArray(new double[0][]);
    }

    public static int[] loadLabels(String filename) throws IOException {
        ArrayList<Integer> labelList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        while ((line = br.readLine()) != null) {
            String[] parts = line.split(",");
            String label = parts[parts.length - 1]; // Last column = class

            if (label.equals("Iris-versicolor")) {
                labelList.add(0); // Assign class 0
            } else if (label.equals("Iris-virginica")) {
                labelList.add(1); // Assign class 1
            }
        }
        br.close();

        return labelList.stream().mapToInt(i -> i).toArray();
    }
}
