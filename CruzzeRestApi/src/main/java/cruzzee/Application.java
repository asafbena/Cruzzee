package cruzzee;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        CoordinateToArea.calculateGeoArea(Collections.emptyList());
        try {
            // TEST
            List areas = new ArrayList<String>();
/*            areas.add("red sea");
            areas.add("arabian sea");
            areas.add("laccadive sea");*/
            areas.add("guinea gulf");
            DangerAnalyzer.getAreaToDanger(areas);

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
