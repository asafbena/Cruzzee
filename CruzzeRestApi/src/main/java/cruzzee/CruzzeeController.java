package cruzzee;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import cruzzee.schemas.Cruzzee;
import cruzzee.schemas.GeoPoint;
import org.springframework.web.bind.annotation.*;

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
        Cruzzee cruzzee = new Cruzzee(routeGeoPoints, startingPoint, destination, departureDate, arrivalDate, cargo);
        return cruzzee;
    }
}
