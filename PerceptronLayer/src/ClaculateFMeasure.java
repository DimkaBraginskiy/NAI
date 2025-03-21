public class ClaculateFMeasure {
    public static double calculateFMeasure(double[] y_actual, double[] y_pred){
        double TP = 0;
        double FP = 0;
        double FN = 0;
        double P = 0;
        double R = 0;
        for(int i = 0; i < y_actual.length; i++){
            if(y_actual[i] == 1 && y_pred[i] == 1){
                TP++;
            }else if(y_actual[i] == 1 && y_pred[i] == 0){
                FN++;
            }else if(y_actual[i] == 0 && y_pred[i] == 1){
                FP++;
            }

            P = (TP)/(TP + FP);

            R = (TP)/(TP + FN);




        }
        return (2*P*R)/(P+R);

    }
}
