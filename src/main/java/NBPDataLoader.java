import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class NBPDataLoader {

    private static final String CurrencyURL = "http://api.nbp.pl/api/exchangerates/rates/a/";

    public static HttpURLConnection connect(String UrlString) throws NullPointerException{
        if (UrlString == null){
            throw new NullPointerException("Parameter is of null value.");
        }
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
    public static HttpURLConnection connect(String currencyCode, String date) throws NullPointerException{
        if (currencyCode == null || date == null){
            throw new NullPointerException("Parameter is of null value.");
        }
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
    public static String read(HttpURLConnection connection) throws NullPointerException{
        if (connection == null){
            throw new NullPointerException("Parameter is of null value.");
        }
        String result;
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
