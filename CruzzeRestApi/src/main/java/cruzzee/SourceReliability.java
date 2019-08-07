package cruzzee;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

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

    public static SearchResults SearchWeb(String searchQuery) throws Exception {
        // Construct the URL.
        URL url = new URL(host + path + "?q=" +  URLEncoder.encode(searchQuery, "UTF-8") + "&count=20");

        // Open the connection.
        HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
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
            if (header.startsWith("BingAPIs-") || header.startsWith("X-MSEdge-")){
                results.relevantHeaders.put(header, headers.get(header).get(0));
            }
        }

        stream.close();
        getProvider(response);
        printUrls(response);
        return results;
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

    public static void getProvider(String response){
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(response).getAsJsonObject();
//        final JsonObject d = json.getAsJsonObject("webPages");
//        final JsonArray results = d.getAsJsonArray("value");
        final JsonArray results = json.getAsJsonArray("value");
        final int resultsLength = results.size();
        for (int i = 0; i < resultsLength; i++) {
            JsonObject aResult = (JsonObject) results.get(i);
            JsonElement d = aResult.get("provider");
            System.out.println(d.getAsJsonArray().get(0).getAsJsonObject().get("name"));
//            return .get("name").getAsString();
        }
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


    public static void download(){

    }


}
