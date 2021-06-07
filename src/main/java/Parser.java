import sun.jvm.hotspot.types.WrongTypeException;

import java.net.HttpURLConnection;
import java.time.LocalDate;
import java.util.NoSuchElementException;

public class Parser {

    private final static int oneDay = 1;
    private final static int weekDays = 7;
    private final static int twoWeekDays = 14;
    private final static int monthDays = 31;
    private final static int quarter = 91;
    private final static int year = 365;

    /**
     *
     * @param currencyCode Kod waluty np USD, CHF
     * @param periodTime Okres czasu dla jakiego metoda pobierze dane (licząc od dnia obecnego wstecz). Przyjmowane są
     *                   następujące wartości: day, week, two weeks, month, quarter, year
     * @return Tablica wartości kursu danej waluty
     *
     */

    public static double [] getCurrency(String currencyCode, String periodTime) throws Exception{
        if (currencyCode == null || periodTime == null){
            throw new NullPointerException("At least one of parameters is null.");
        }
        int days = 0;
        switch (periodTime) {
            case "day":
                days = oneDay;
                break;
            case "week":
                days = weekDays;
                break;
            case "two weeks":
                days = twoWeekDays;
                break;
            case "month":
                days = monthDays;
                break;
            case "quarter":
                days = quarter;
                break;
            case "year":
                days = year;
                break;
            default:
                return null;
        }

        double [] temp = new double[days];
        int resultSize = 0;
        for(int i = 1; i <= days; i++){
            HttpURLConnection connection = NBPDataLoader.connect(currencyCode,LocalDate.now().minusDays(i).toString());
            String answer = NBPDataLoader.read(connection);
            if(answer == null){
                temp[i] = 0;
                continue;
            }
            else if(answer.equals("404 NotFound")){
                throw new NoSuchElementException("No currency for code \"" + currencyCode + "\" found.");
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