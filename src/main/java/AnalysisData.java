import java.util.Arrays;

/**
 *
 * AnalysisData - Klasa zawierająca metody pozwalajace wyznaczyć określone wartości np mediana, dominanta, liczba sesji
 * wzrostowych itd.
 *
 */

public class AnalysisData {

    public static double getMedian(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        double median;
        double avg;
        Arrays.sort(data);
        if (data.length % 2 == 0 ) {
            avg = data[data.length/2] + data[(data.length/2)-1];
            median = avg/2.0;

        }
        else{
            median = data[data.length/2];
        }
        return median;
    }

    public static double getDominant(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        int dominantIdx = 0;
        int counter1 = 0;
        int counter2;
        for(int i = 0; i < data.length; i++){
            counter2 = 0;
            for (double x : data) {
                if (data[i] == x) {
                    counter2++;
                }
            }
            if(counter2 > counter1){
                counter1 = counter2;
                dominantIdx = i;
            }
        }

        return data[dominantIdx];
    }

    public static double getStandardDeviation(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        double sum = 0.0;
        double standardDeviation = 0.0;
        int length = data.length;

        for(double num : data) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: data) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);

    }

    public static double coefficientOfVariation(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        double avg = 0;
        for(double d:data){
            avg += d;
        }
        avg = avg / data.length;

        return getStandardDeviation(data)/avg;
    }

    public static int upSession(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        int upSessionCounter = 0;
        int counter = 1;
        for(int i = 1; i < data.length; i++){
            if(data[i - 1] < data[i]){
                if(counter != 0) {
                    counter = 0;
                    upSessionCounter++;
                }
            }
            else {
                counter++;
            }
        }
        return upSessionCounter;
    }

    public static int downSession(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        int upSessionCounter = 0;
        int counter = 1;
        for(int i = 1; i < data.length; i++){
            if(data[i - 1] > data[i]){
                if(counter != 0) {
                    counter = 0;
                    upSessionCounter++;
                }
            }
            else {
                counter++;
            }
        }
        return upSessionCounter;
    }

    public static int stableSession(double[] data) throws NullPointerException{
        if (data == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        int upSessionCounter = 0;
        int counter = 1;
        for(int i = 1; i < data.length; i++){
            if(data[i - 1] == data[i]){
                if(counter != 0) {
                    counter = 0;
                    upSessionCounter++;
                }
            }
            else {
                counter++;
            }
        }
        return upSessionCounter;
    }
}
