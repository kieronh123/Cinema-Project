package Tills.TillsSystem.sample;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import JSON.JSON;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import Tills.TillsSystem.sample.Movie;

import javax.net.ssl.HttpsURLConnection;

public class Harness {

    private static final String USER_AGENT = "Mozilla/5.0";

    private static void main(String[] args) throws Exception {

        Harness http = new Harness();

        System.out.println("Testing 1 - Send Http GET request");
<<<<<<< HEAD
=======
        sendGet("");
>>>>>>> bb84fcd2d90fd4c29713dc793ced3cf4a1714a48

        //System.out.println("\nTesting 2 - Send Http POST request");
        //http.sendPost();

    }

    // HTTP GET request
<<<<<<< HEAD
    private void sendGet(String command) throws Exception {

=======
    private static void sendGet(String command) throws Exception {
        System.out.println("here");
>>>>>>> bb84fcd2d90fd4c29713dc793ced3cf4a1714a48
        String url = "http://localhost:5000/users/";

        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        // optional default is GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        User[] users = JSON.usersFromJson(response.toString());
        for(User user: users){
            System.out.println(user.toString());
        }

        //print result
        System.out.println(response.toString());

    }



    // HTTP POST request
    private void sendPost() throws Exception {

        String url = "https://selfsolve.apple.com/wcResults.do";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

}