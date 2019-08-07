package cruzzee;

import cruzzee.coordinateapi.CoordinateToArea;
import cruzzee.schemas.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;

//import static cruzzee.ContentDownloader.getPagesForAllAreas;

@RestController
public class CruzzeeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @RequestMapping(value = "/risk", method = RequestMethod.POST)
    public ResponseEntity<List<Risk>> persistPerson(@RequestBody Trip trip) {

        List<Risk> risks = new ArrayList<>();

        try {
            Map<GeoLine, GeoLocationDefinition> areaToCoordinateStream = CoordinateToArea.getAreaToCoordinateStream(trip.getRoute());
            Set<GeoLine> results = areaToCoordinateStream.keySet();

            for (GeoLine geoLine : results) {
                String country = areaToCoordinateStream.get(geoLine).getCountry();
                String area = areaToCoordinateStream.get(geoLine).getArea();
                risks.add(new Risk(geoLine, new RiskDescription(
                        DangerAnalyzer.getDangerForArea(area, country), "matan fleishman")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok(risks);
    }
}
