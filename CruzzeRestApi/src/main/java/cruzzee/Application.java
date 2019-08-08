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

import static cruzzee.SourceReliability.SearchWeb;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        try {
//            SearchWeb("washington post");
/*            // TEST
            List areas = new ArrayList<String>();
            DangerAnalyzer.getDangerForArea("gulf of persia", "iran", "oil");*/

        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(Application.class, args);
    }
}
