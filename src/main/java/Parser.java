import java.net.HttpURLConnection;
import java.time.LocalDate;

public class Parser {

    private final static int weekDays = 7;
    private final static int twoWeekDays = 14;
    private final static int monthDays = 31;
    private final static int quarter = 91;
    private final static int year = 365;

    public static double [] getCurrencyWeek(String currencyCode){

        double [] temp = new double[weekDays];
        int resultSize = 0;
        for(int i = 0; i < weekDays; i++){
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
        for(int i = 0; i < weekDays; i++){
            if(temp[i] != 0){
                result[idx] = temp[i];
                idx++;
            }
        }
        return result;
    }
}
