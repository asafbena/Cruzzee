package cruzzee;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cruzzee.schemas.*;
import org.springframework.web.bind.annotation.*;

//import static cruzzee.ContentDownloader.getPagesForAllAreas;

@RestController
public class CruzzeeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/cruzzee/routeGeoPoints={routeGeoPoints}&startingPoint={startingPoint}&destination={destination}" +
            "&departureDate={departureDate}&cargo={cargo}")
    public AreaRisks greeting(@PathVariable("routeGeoPoints") List<Double> routeGeoPoints,
                              @PathVariable String startingPoint,
                              @PathVariable String destination,
                              @PathVariable long departureDate,
                              @PathVariable String cargo
                             ) {
        List<GeoPoint> geoPoints = GeoPoint.getPointsFromCoordinates(routeGeoPoints);
        AreaRisks areaRisks = new AreaRisks();
//        From SourceAnalyzeApi
//        Map<GeoLine, String> geoLineAreaMap = getLineAreaMap(geoPoints);

//        List<Page> pages = getPagesForAllAreas(geoLineAreaMap);
        //analyze every page to get its risk(occurrence*consequences)
        // get the final risk after recalculated in order to match the author reliability
        // the numerical risk will be attached to its area and will be returned eventually to web
//        Cruzzee cruzzee = new Cruzzee(startingPoint,
//                destination,
//                departureDate,
//                arrivalDate,
//                cargo);
//        return cruzzee;

        return null;

    }
}
