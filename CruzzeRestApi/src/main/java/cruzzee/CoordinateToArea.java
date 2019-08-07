package cruzzee;

import cruzzee.schemas.GeoArea;
import cruzzee.schemas.GeoLine;
import cruzzee.schemas.GeoPoint;
import org.geonames.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static cruzzee.AreaDeduction.getMiddlePoint;

public class CoordinateToArea {
    public static HashMap<GeoLine, GeoArea> calculateGeoArea(List<GeoLine> geoLines) {
        HashMap<GeoLine, GeoArea> geoLinesToGeoAreas = new HashMap<>();

        for (GeoLine geoLine : geoLines) {
            HashMap<GeoLine, GeoArea> dividedMap;
            try {
                dividedMap = divideAndUnify(geoLine);
            } catch (Exception e) {
                e.printStackTrace();
                break;
            }

            for (GeoLine dividedLine : dividedMap.keySet()) {
                geoLinesToGeoAreas.put(geoLine, dividedMap.get(dividedLine));
            }
        }

        return geoLinesToGeoAreas;
    }

    private static HashMap<GeoLine, GeoArea> divideAndUnify(GeoLine geoLine) throws Exception {
        HashMap<GeoLine, GeoArea> geoMap = new HashMap<>();

        // Get start and end points of geoline
        GeoPoint point1 = new GeoPoint(geoLine.getStartX(), geoLine.getStartY());
        GeoPoint point2 = new GeoPoint(geoLine.getEndX(), geoLine.getEndY());

        // Calculate geoline middle point
        GeoPoint middlePoint = getMiddlePoint(point1, point2);

        // Calculate middle points of divided geolines
        GeoPoint middlePoint1 = getMiddlePoint(point1, middlePoint);
        GeoPoint middlePoint2 = getMiddlePoint(middlePoint, point2);

        // Calculate information for divided geolines
        GeoArea geoArea1 = returnAreaAndCountry(middlePoint1);
        GeoArea geoArea2 = returnAreaAndCountry(middlePoint2);

        // Determine whether the two divided lines share the same information,
        // if so, unify the lines
        if (geoArea1.getSeaArea().equals(geoArea2.getSeaArea()) &&
            geoArea1.getCountryName().equals(geoArea2.getCountryName())) {
            GeoArea geoArea = returnAreaAndCountry(middlePoint);
            geoMap.put(geoLine, geoArea);
            return geoMap;
        }

        GeoLine line1 = new GeoLine(point1.getXCoordinate(), point1.getYCoordinate(),
                middlePoint.getXCoordinate(), middlePoint.getYCoordinate());
        GeoLine line2 = new GeoLine(middlePoint.getXCoordinate(), middlePoint.getYCoordinate(),
                point2.getXCoordinate(), point2.getYCoordinate());

        geoMap.put(line1, geoArea1);
        geoMap.put(line2, geoArea1);
        return geoMap;
    }

    public static GeoArea returnAreaAndCountry(GeoPoint point) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        String area = getAreaName(point);
        String country = getCountryName(point);
        double distance = getDistanceFromCountry(point);

        return new GeoArea(area, country, distance);
    }

    private static String getAreaName(GeoPoint point) throws Exception {
        return WebService.ocean(point.getXCoordinate(), point.getYCoordinate());
    }

    private static String getCountryName(GeoPoint point) throws Exception {
        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(point.getXCoordinate(), point.getYCoordinate());
        return nearbyPlaceName.get(0).getCountryName();
    }

    private static double getDistanceFromCountry(GeoPoint point) throws Exception {
        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(point.getXCoordinate(), point.getYCoordinate());

        double countryXCoordinate = nearbyPlaceName.get(0).getLatitude();
        double countryYCoordinate = nearbyPlaceName.get(0).getLongitude();
        GeoPoint countryGeoPoint = new GeoPoint(countryXCoordinate, countryYCoordinate);
        return calculateDistance(point, countryGeoPoint);
    }

    private static double calculateDistance(GeoPoint point1, GeoPoint point2) {
        return Math.hypot(point1.getXCoordinate()-point2.getYCoordinate(),
                point1.getYCoordinate()-point2.getYCoordinate());
    }
}
