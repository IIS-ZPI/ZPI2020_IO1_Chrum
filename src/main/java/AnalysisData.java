import java.util.Arrays;

/**
 * AnalysisData - klosa zawiera metody pozwalajac wyznaczyć określone wartości np mediana, dominanta, liczba sesji wzrostowych itd.
 */
public class AnalysisData {

    public static double getMedian(double [] data){

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

    public static double getDominant(double [] data){
        int dominantIdx = 0;
        int counter1 = 0;
        int counter2;
        for(int i = 0; i < data.length; i++){
            counter2 = 0;
            for (int x = 0; x < data.length; x++){
                if( data[i] == data[x]){
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

    public static double getStandardDeviation(double [] data){

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

    public static double coefficientOfVariation(double [] data){
        double avg = 0;
        for(double d:data){
            avg += d;
        }
        avg = avg / data.length;

        return getStandardDeviation(data)/avg;
    }

    public static int upSession(double [] data){
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

    public static int downSession(double [] data){
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

    public static int stableSession(double [] data){
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
