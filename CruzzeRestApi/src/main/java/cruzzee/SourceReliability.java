package cruzzee;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.customsearch.Customsearch;
import com.google.api.services.customsearch.model.Result;
import com.google.api.services.customsearch.model.Search;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import cruzzee.schemas.GeoLine;
import cruzzee.schemas.Page;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.Month;
import java.util.*;

public class SourceReliability {
    private static final int HTTP_REQUEST_TIMEOUT = 3 * 600000;


    // Enter a valid subscription key.
    static String subscriptionKey = "b028322b5f5e4baaa0c1c557a3ed2cde";

    /*
     * If you encounter unexpected authorization errors, double-check these values
     * against the endpoint for your Bing Web search instance in your Azure
     * dashboard.
     */
    static String host = "https://api.cognitive.microsoft.com";
    static String path = "/bing/v7.0/news/search/";
    static String searchTerm = "Microsoft Cognitive Services";

    public static double SearchWeb(String searchQuery) throws Exception {
        // Construct the URL.
//        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=20");

        long now = Instant.now().getEpochSecond();//System.currentTimeMillis() / 1000L;
        long miliseconds_in_hour = 3600000;
        long since = now - (24 * miliseconds_in_hour);

        System.out.println("now = " + now);
        System.out.println("since = " + since);

        URL day = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=50&sortBy=Date");
//        URL last_X_hours = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=100&sortBy=Date&since="+since);
        //URL week = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=100&freshness=WEEK");
        //URL month = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=100&freshness=MONTH");

        double divide = getCount(day);
//        int last_X_hours_count = getCount(last_X_hours);

//        double chance = (day_count) / last_X_hours_count;

//        int week_count = getCount(week);
//        int month_count = getCount(month);


//        System.out.println("day-count = " + day_count);
//        System.out.println("last_X_hours_count = " + last_X_hours_count);
//        System.out.println("chance = " + chance);

        //printUrls(response);
        //return results;
        return divide;
    }


    public static double getCount(URL url) throws Exception{
        // Open the connection.
        try {


            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);

            // Receive the JSON response body.
            InputStream stream = connection.getInputStream();
            String response = new Scanner(stream).useDelimiter("\\A").next();

            // Construct the result object.
            SearchResults results = new SearchResults(new HashMap<String, String>(), response);

            // Extract Bing-related HTTP headers.
            Map<String, List<String>> headers = connection.getHeaderFields();
            for (String header : headers.keySet()) {
                if (header == null) continue;      // may have null key
                if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")) {
                    results.relevantHeaders.put(header, headers.get(header).get(0));
                }
            }
            stream.close();

            return getProvider(response);
        }
        catch(IOException e)
        {
            System.out.println("FUCKKKK");
        }
        return 1;
    }

    public static double getProvider(String response) throws ParseException {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
//        final JsonObject d = json.getAsJsonObject("webPages");
//        final JsonArray results = d.getAsJsonArray("value");
        final JsonArray results = json.getAsJsonArray("value");
        int count=0;

        //long now = Instant.now().getEpochSecond();//System.currentTimeMillis() / 1000L;
        long miliseconds_in_hour = 3600000;
        //long since = now - (4 * miliseconds_in_hour);
        Date X_hours_ago = new Date(System.currentTimeMillis()-(int)(3 *miliseconds_in_hour));
        Date now = new Date(System.currentTimeMillis()-0*miliseconds_in_hour);

        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//        Date now = dateFormat.parse(new Timestamp(System.currentTimeMillis()).toString());
      //  Date X_hours_ago = new Timestamp(System.currentTimeMillis()-4*miliseconds_in_hour).toString();
//        String strNow = now.toString();
//        System.out.println("!!!! count = " + strNow.substring(strNow.indexOf('T')+1,strNow.indexOf('.')));

//        Date currentHour = dateFormat.parse(now).toString().substring(now.indexOf('T')+1,now.indexOf('.')));
//        Date X_ago_hour = dateFormat.parse(X_hours_ago.substring(X_hours_ago.indexOf('T')+1,X_hours_ago.indexOf('.')));

        final int resultsLength = results.size();
        double total_result = 1;
        for (int i = 0; i < resultsLength - 1; i++) {
            JsonObject aResult = (JsonObject) results.get(i);
            JsonObject aResult2 = (JsonObject) results.get(i+1);

//            JsonElement d = aResult.get("provider");

            String date = aResult.get("datePublished").getAsString();
            String date2 = aResult2.get("datePublished").getAsString();

            System.out.println("check : " + date);
            //String time = date.substring(date.indexOf('T')+1,date.indexOf('.'));
            String[] strings = date.split("T");
            String[] strings2 = date2.split("T");

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm");
            df.setTimeZone(TimeZone.getTimeZone("IDT"));

            String year = strings[0];
            String time = strings[1];
            String checkTIme =  time.substring(0,time.indexOf('.'));
            Date parsedD = df.parse(year + " " + checkTIme);

            String year2 = strings2[0];
            String time2 = strings2[1];
            String checkTIme2 =  time2.substring(0,time2.indexOf('.'));
            Date parsedD2 = df.parse(year2 + " " + checkTIme2);

            double divide = (double) (parsedD2.getTime()) / parsedD.getTime();
//            double diff = (double) (parsedD2.getTime()) - parsedD.getTime();
//            System.out.println("diff : " + diff);

//            if(diff!=0)
                total_result*=divide;

            System.out.println("time : " + parsedD);


//            Date parsedDate = (Date) dateFormat.parse(time);
//            Date date1 = new Date();
//            date1.setYear();
//            date1.setMonth();
//            date1.setDate();
//            date1.se

//            if(parsedD.before(now) && parsedD.after(X_hours_ago))
//                count++;


//            Date parsedDate = dateFormat.parse(date);
//            System.out.println("!!!! parsedDate = " + parsedDate.getTime());
            //if (date > since)
              //  count++;
           // System.out.println(d.getAsJsonArray().get(0).getAsJsonObject().get("name"));
//            return .get("name").getAsString();
        }

        System.out.println("!!!! total_result = " + total_result);
        System.out.println("!!!! results.size() = " + results.size());
        //double r = (count * 6)/results.size();
        return total_result;
    }

    private static void printUrls(String response) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
//        final JsonObject d = json.getAsJsonObject("webPages");
//        final JsonArray results = d.getAsJsonArray("value");
        final JsonArray results = json.getAsJsonArray("value");
        final int resultsLength = results.size();
        for (int i = 0; i < resultsLength; i++) {
            final JsonObject aResult = (JsonObject) results.get(i);
            System.out.println(aResult.get("url"));
            String url = aResult.get("url").getAsString();
//            downloadWebContent(url);
        }
    }

    public static void downloadWebContent(String urlPath)
    {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            url = new URL(urlPath);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (MalformedURLException mue) {
            mue.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }

    public static void download(){

    }


}
