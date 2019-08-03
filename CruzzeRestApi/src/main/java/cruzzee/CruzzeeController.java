package cruzzee;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import cruzzee.schemas.Cruzzee;
import cruzzee.schemas.GeoLine;
import cruzzee.schemas.GeoPoint;
import cruzzee.schemas.Page;
import org.springframework.web.bind.annotation.*;

import static cruzzee.ContentDownloader.getPagesForAllAreas;

@RestController
public class CruzzeeController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/cruzzee/routeGeoPoints={routeGeoPoints}&startingPoint={startingPoint}&destination={destination}" +
            "&departureDate={departureDate}&arrivalDate={arrivalDate}&cargo={cargo}")
    public Cruzzee greeting(@PathVariable("routeGeoPoints") List<Double> routeGeoPoints,
                             @PathVariable String startingPoint,
                             @PathVariable String destination,
                             @PathVariable long departureDate,
                             @PathVariable long arrivalDate,
                             @PathVariable String cargo
                             ) {
        List<GeoPoint> geoPoints = GeoPoint.getPointsFromCoordinates(routeGeoPoints);
//        From SourceAnalyzeApi
//        Map<GeoLine, String> geoLineAreaMap = getLineAreaMap(geoPoints);

//        List<Page> pages = getPagesForAllAreas(geoLineAreaMap);
        //analyze every page to get its risk(occurrence*consequences)
        // get the final risk after recalculated in order to match the author reliability
        // the numerical risk will be attached to its area and will be returned eventually to web
        Cruzzee cruzzee = new Cruzzee(startingPoint,
                destination,
                departureDate,
                arrivalDate,
                cargo);
        return cruzzee;
    }
}
