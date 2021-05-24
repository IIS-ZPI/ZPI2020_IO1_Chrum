import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class NBPDataLoader {

    private final String BaseURL = "http://api.nbp.pl/api/";

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

    public static String read(HttpURLConnection connection){
        String result = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));
            result = br.readLine();
        }
        catch (IOException e){
            System.out.println("Input/Output error!");
        }

        return result;
    }

}
