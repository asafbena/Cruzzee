package cruzzee;

import org.springframework.boot.SpringApplication;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
            // TEST
            List areas = new ArrayList<String>();
/*            areas.add("red sea");
            areas.add("arabian sea");
            areas.add("laccadive sea");*/
            areas.add("persian sea iran");
            DangerAnalyzer.getDangerForArea("persian gulf", "iran");

        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(Application.class, args);
    }
}
