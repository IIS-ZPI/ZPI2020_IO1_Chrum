import java.util.Arrays;

/**
 *
 * AnalysisData - Klasa zawierająca metody pozwalajace wyznaczyć określone wartości np mediana, dominanta, liczba sesji
 * wzrostowych itd.
 *
 * @field distributionChangesRanges - zawiera zakresy rozkładu (ustawia je funkcja distributionChanges)
 *
 */

public class AnalysisData {


    private static double [] distributionChangesRanges;

    private static void setDistributionChangesRanges(double[] distributionChangesRanges) {
        AnalysisData.distributionChangesRanges = distributionChangesRanges;
    }

    public static double[] getDistributionChangesRanges() {
        return distributionChangesRanges;
    }


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


    public static int[] distributionChanges(double [] firstCurrency, double []secondCurrency){
        if(firstCurrency.length != secondCurrency.length){
            return null;
        }
        double []sub = new double[firstCurrency.length];
        double []diff = new double[firstCurrency.length - 1];

        for(int i = 0; i < firstCurrency.length; i++){
            sub[i] = firstCurrency[i] - secondCurrency[i];
        }

        for(int i = 0; i < firstCurrency.length - 1; i++){
            diff[i] = sub[i] - sub[i + 1];
        }

        double min = Arrays.stream(diff).min().getAsDouble();
        double max = Arrays.stream(diff).max().getAsDouble();

        double temp = max - min;
        temp /= 10;
        temp = Math.round(temp * 10000)/10000.00;


        int [] counter = new int[12];
        double [] values = new double[11];
        for(int i = 1; i <= 5; i++){
            values[5 - i] = -(i*temp);
        }
        for(int i = 1; i <= 5; i++){
            values[5 + i] = (i*temp);
        }

        for (double v : diff) {
            if (v <= values[0]) {
                counter[0]++;
            } else if (v < values[1]) {
                counter[1]++;
            } else if (v < values[2]) {
                counter[2]++;
            } else if (v < values[3]) {
                counter[3]++;
            } else if (v < values[4]) {
                counter[4]++;
            } else if (v < values[5]) {
                counter[5]++;
            } else if (v < values[6]) {
                counter[6]++;
            } else if (v < values[7]) {
                counter[7]++;
            } else if (v < values[8]) {
                counter[8]++;
            } else if (v < values[9]) {
                counter[9]++;
            } else if (v < values[10]) {
                counter[10]++;
            } else {
                counter[11]++;
            }
        }

        AnalysisData.setDistributionChangesRanges(values);
        return counter;
    }


    public static void printDistributionChanges(int [] counter) throws NullPointerException{
        if (distributionChangesRanges == null)
            throw new NullPointerException("Podano nieprawidłową walutę.");
        for (int i = 0; i <= distributionChangesRanges.length; i++){
            if (i==0){
                System.out.print("<" + distributionChangesRanges[i] + ": ");
            }
            else if (i == distributionChangesRanges.length){
                System.out.print(">" + distributionChangesRanges[i-1] + ": ");
            }
            else {
                System.out.print(distributionChangesRanges[i-1] + " - " + distributionChangesRanges[i] + ": ");
            }

            System.out.println(counter[i]);
            System.out.println();
        }
    }
}
