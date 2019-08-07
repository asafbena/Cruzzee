package cruzzee;

import cruzzee.schemas.GeoPoint;
import org.springframework.boot.SpringApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@SpringBootApplication
public class Application {

    private static double getDoubleMiddle(double value1, double value2) {
        return (value1 + value2) / 2;
    }

    private static GeoPoint getMiddlePoint(GeoPoint point1, GeoPoint point2) {
        return new GeoPoint(
                getDoubleMiddle(point1.getXCoordinate(), point2.getXCoordinate()),
                getDoubleMiddle(point1.getYCoordinate(), point2.getYCoordinate())
        );
    }

    private static List<GeoPoint> getPointsStream() {
        return null;
    }

    public static void main(String[] args) {
        List<GeoPoint> points = getPointsStream();
        for (int i = 0; i < points.size(); i+=2) {
            GeoPoint averagePoint = getMiddlePoint(points.get(i), points.get(i + 1));

        }
        return;
        try {
            SearchResults result = ContentDownloader.SearchWeb("gulf+of+persian+oil+ship+attack");
            for (String header : result.relevantHeaders.keySet())
                System.out.println(header + ": " + result.relevantHeaders.get(header));
            System.out.println("\nJSON Response:\n");
            System.out.println(prettify(result.jsonResponse));
        } catch (Exception e) {
            e.printStackTrace();
        }
//        ContentDownloader.search("gulf of persian oil ship attack");
        SpringApplication.run(Application.class, args);
    }

    public static String prettify(String json_text) {
        JsonParser parser = new JsonParser();
        JsonObject json = parser.parse(json_text).getAsJsonObject();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(json);
    }

}
