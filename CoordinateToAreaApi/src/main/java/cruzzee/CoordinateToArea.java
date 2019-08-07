package cruzzee;

import org.geonames.Toponym;
import org.geonames.WebService;
import schemas.GeoLocationDefinition;
import schemas.GeoLine;
import schemas.GeoPoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinateToArea {
    public static Map<GeoLine, GeoLocationDefinition> getAreaToCoordinateStream(List<GeoLine> geoLines) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        Map<GeoLine, GeoLocationDefinition> geoPointsDefinitions = new HashMap<>();
        for (GeoLine geoLine: geoLines) {
            geoPointsDefinitions.put(geoLine, calculateLocationDefinition(geoLine));
        }
        System.out.println("Done");
        return geoPointsDefinitions;
    }

    private static GeoLocationDefinition calculateLocationDefinition(GeoLine geoLine) throws Exception {
        GeoPoint middlePoint = AreaDeduction.getMiddlePoint(geoLine);
        String area = getArea(middlePoint);
        String country = getCountry(middlePoint);
        return new GeoLocationDefinition(area, country);
    }

    private static String getCountry(GeoPoint geoPoint) throws Exception {
        List<Toponym> toponyms =
                WebService.findNearbyPlaceName(geoPoint.getXCoordinate(),geoPoint.getYCoordinate());
        return toponyms.get(0).getCountryName();
    }

    private static String getArea(GeoPoint geoPoint) throws Exception {
        return WebService.ocean(geoPoint.getXCoordinate(), geoPoint.getYCoordinate());
    }
}
