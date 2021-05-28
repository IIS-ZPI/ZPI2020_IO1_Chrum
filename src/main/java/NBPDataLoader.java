import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class NBPDataLoader {

    private final String BaseURL = "http://api.nbp.pl/api/";
    private static final String CurrencyURL = "http://api.nbp.pl/api/exchangerates/rates/a/";

    public static HttpURLConnection connect(String UrlString){

        HttpURLConnection connection = null;
        URL url;

        try {
            url = new URL(UrlString);
            connection = (HttpURLConnection) url.openConnection();
        }
        catch (MalformedURLException e){
            System.out.println("Url error!");
        }
        catch (IOException e){
            System.out.println("Connection error!");
        }

        return connection;
    }

    /**
     *
     * @param currencyCode kod waluty
     * @param date data, dla której będziemy pobierać kurs waluty
     * @return uchwyt do połączenia z bazą, zktórej można pobrać dane
     */
    public static HttpURLConnection connect(String currencyCode, String date){

        HttpURLConnection connection = null;
        URL url;

        try {
            url = new URL(CurrencyURL + currencyCode + '/' + date + '/');
            connection = (HttpURLConnection) url.openConnection();
        }
        catch (MalformedURLException e){
            System.out.println("Url error!");
        }
        catch (IOException e){
            System.out.println("Connection error!");
        }

        return connection;
    }

    /**
     *
     * @param connection uchwyt połączenia z bazą danych
     * @return  dane pobrane z bazy (jako string)
     */
    public static String read(HttpURLConnection connection){
        String result = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            result = br.readLine();
        }
        catch (IOException e){
            return null;
        }

        return result;
    }

}
