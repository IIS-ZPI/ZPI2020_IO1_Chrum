import java.net.HttpURLConnection;
import java.time.LocalDate;

public class Parser {

    private final static int oneDay = 1;
    private final static int weekDays = 7;
    private final static int twoWeekDays = 14;
    private final static int monthDays = 31;
    private final static int quarter = 91;
    private final static int year = 365;

    /**
     *
     * @param currencyCode kod waluty np USD, CHF
     * @param periodTime okres czasu dla jakiego metoda pobierze dane(licząc od dnia obecnego wstecz). Przyjmowane są następujące wartości: day, week,
     *                   two weeks, month, quarter, year
     * @return tablica wartości kursu danej waluty
     */
    public static double [] getCurrency(String currencyCode, String periodTime){

        int days = 0;

        if(periodTime.equals("day")){
            days = oneDay;
        }else if(periodTime.equals("week")){
            days = weekDays;
        }else if(periodTime.equals("two weeks")) {
            days = twoWeekDays;
        }else if(periodTime.equals("month")){
            days = monthDays;
        }else if(periodTime.equals("quarter")){
            days = quarter;
        }else if(periodTime.equals("year")){
            days = year;
        }
        else {
            return null;
        }

        double [] temp = new double[days];
        int resultSize = 0;
        for(int i = 0; i < days; i++){
            HttpURLConnection connection = NBPDataLoader.connect(currencyCode,LocalDate.now().minusDays(i).toString());
            String answer = NBPDataLoader.read(connection);
            if(answer == null){
                temp[i] = 0;
                continue;
            }
            answer = answer.substring(answer.lastIndexOf("mid"));
            answer = answer.substring(5).replaceAll("]", "").replaceAll("}","");
            temp[i] = Double.parseDouble(answer);
            resultSize++;
        }

        double[] result = new double[resultSize];
        int idx = 0;
        for(int i = 0; i < days; i++){
            if(temp[i] != 0){
                result[idx] = temp[i];
                idx++;
            }
        }
        return result;
    }
}