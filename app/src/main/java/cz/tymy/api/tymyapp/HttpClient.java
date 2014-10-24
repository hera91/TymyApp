package cz.tymy.api.tymyapp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class HttpClient {
    public String mUrlBase;
    //public static final String SERVER = "http://10.35.2.186:8080";
    private final String USER_AGENT = "Mozilla/5.0";
    private static final String TAG = "BurpeeApp";
    private static final int OK = 200;

    public HttpClient(){}

    // Json GET request
    public String getJson(String url) throws Exception {
        return sendGet(url);
    }

    // Json GET request
    public String postStatus(String name, String status, String comment) throws Exception {
        return sendPost(mUrlBase, name, status, comment);
    }

    // Json GET request
    public String postStatus(String url, String name, String status, String comment)
            throws Exception {
        return sendPost(url, name, status, comment);
    }

    // HTTP GET request
    public String sendGet(String url) throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

        if (responseCode == OK) {

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //return result
            return response.toString();
        }
        else
            return null;
    }

    // HTTP POST request
    private String sendPost(String url, String name, String status, String comment)
            throws Exception {

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        //con.setRequestProperty("Content-Type", "application/json");

        String urlParameters = "name=" + URLEncoder.encode(name, "UTF-8")
                + "&status=" + URLEncoder.encode(status, "UTF-8")
                + "&comment=" + URLEncoder.encode(comment, "UTF-8");
        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //return result
        return response.toString();
    }
}
