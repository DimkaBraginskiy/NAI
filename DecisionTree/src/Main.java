import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        // x1 = {1,1,,2,4}
        // y =  {1,2,3,5}
        // th = 1.5
        //RSS = 2.5

        double[] x1 = {1,1,2,4}; //coordinates of x1
        double[] y = {1,2,3,5};  //labels corresponding to x1's coordinates

        Scanner sc = new Scanner(System.in);
        System.out.print("Input the threshold: ");
        double threshold = sc.nextDouble();


        //double RSS = nodeRSS(x1, y, threshold);
        nodeRSS(x1, y, threshold);

    }

    public static void nodeRSS(double[] x, double[] y, double threshold){
        List<Double> r1 = new ArrayList<>(); // true dataset
        List<Double> r2 = new ArrayList<>(); //  dataset

        for(int i = 0; i< x.length; i++){
            if(x[i] <= threshold){
                r1.add(y[i]);
            }else{
                r2.add(y[i]);
            }
        }

        System.out.println("r1 labels: " + List.of(r1));
        System.out.println("r2 labels: " + List.of(r2));

        double y1Sum = 0.0;
        for(double num : r1){
            y1Sum += num;
        }
        double y2Sum = 0.0;
        for(double num : r2){
            y2Sum += num;
        }

        double y1Mean = (y1Sum / r1.size());
        double y2Mean = (y2Sum / r2.size());

        double[] means = {y1Mean, y2Mean};

        System.out.println("Means: " + Arrays.toString(means));

        double[] r1Arr = new double[r1.size()];
        double[] r2Arr = new double[r2.size()];
        for(int i = 0; i< r1.size(); i++){
            r1Arr[i] = r1.get(i);
            r2Arr[i] = r2.get(i);
        }

        double result1 = RSS(r1Arr, y1Mean);
        double result2 = RSS(r2Arr, y2Mean);

        double res = result1 + result2;


        System.out.println(res);
    }

    private static double RSS(double[] y_true, double y_pred){
        double res = 0.0;

        for(int i = 0; i < y_true.length; i++){
            res += Math.pow(y_true[i] - y_pred, 2);
            System.out.println(res);
        }
        return res;
    }
}