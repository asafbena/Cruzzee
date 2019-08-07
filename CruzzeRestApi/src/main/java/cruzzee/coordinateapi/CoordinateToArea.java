package cruzzee.coordinateapi;

import cruzzee.schemas.GeoLine;
import cruzzee.schemas.GeoLocationDefinition;
import cruzzee.schemas.GeoPoint;
import org.geonames.Toponym;
import org.geonames.WebService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinateToArea {
    public static Map<GeoLine, GeoLocationDefinition> getAreaToCoordinateStream(List<GeoLine> geoLines) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        Map<GeoLine, GeoLocationDefinition> geoPointsDefinitions = new HashMap<>();
        for (GeoLine geoLine : geoLines) {
            geoPointsDefinitions.put(geoLine, calculateLocationDefinition(geoLine));
        }
        System.out.println("Done");
        return geoPointsDefinitions;
    }

    private static GeoLocationDefinition calculateLocationDefinition(GeoLine geoLine) throws Exception {
        GeoPoint middlePoint = AreaDeduction.getMiddlePoint(geoLine);
        String area = getArea(middlePoint);
        String country = getCountry(middlePoint);
        double lineDistance = getDistanceFromCountry(middlePoint);
        return new GeoLocationDefinition(area, country, lineDistance);
    }

    private static String getArea(GeoPoint geoPoint) throws Exception {
        return WebService.ocean(geoPoint.getLatitude(), geoPoint.getLongitude());
    }

    private static String getCountry(GeoPoint geoPoint) throws Exception {
        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(geoPoint.getLatitude(), geoPoint.getLongitude());
        if (!nearbyPlaceName.isEmpty()) {

            Toponym firstCountryObject =
                    nearbyPlaceName.get(0);
            return firstCountryObject.getCountryName();
        }
        return "";
    }

    private static double getDistanceFromCountry(GeoPoint geoPoint) throws Exception {
        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(geoPoint.getLatitude(), geoPoint.getLongitude());
        if(!nearbyPlaceName.isEmpty()) {
            Toponym firstCountryObject =
                    nearbyPlaceName.get(0);
            double countryXCoordinate = firstCountryObject.getLatitude();
            double countryYCoordinate = firstCountryObject.getLongitude();
        }

        return 0;
    }
}
