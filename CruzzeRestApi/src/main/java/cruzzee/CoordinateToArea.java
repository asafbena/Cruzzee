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

//    private static HashMap<GeoLine, GeoArea> unifyGeoLines(HashMap<GeoLine, GeoArea> geoMap) {
//        for (GeoLine geoline: geoMap.keySet()) {
//            geoMap.get(geoline).;
//        }
//    }

//    private static ArrayList<GeoLine> divideAndUnify(GeoLine geoLine) {
//        ArrayList<GeoLine> geoLines = new ArrayList<GeoLine>();
//        GeoPoint point1 = new GeoPoint(geoLine.getStartX(), geoLine.getStartY());
//        GeoPoint point2 = new GeoPoint(geoLine.getEndX(), geoLine.getEndY());
//
//        GeoPoint middlePoint = getMiddlePoint(point1, point2);
//        GeoLine lineX = new GeoLine(point1.getXCoordinate(), point1.getYCoordinate(),
//                                    middlePoint.getXCoordinate(), middlePoint.getYCoordinate());
//        GeoLine lineY = new GeoLine(middlePoint.getXCoordinate(), middlePoint.getYCoordinate(),
//                                    point2.getXCoordinate(), point2.getYCoordinate());
//
//
//    }

    public static HashMap<GeoLine, GeoArea> calculateGeoArea(List<GeoLine> geoLines) throws Exception {
        HashMap<GeoLine, GeoArea> geoLinesToGeoAreas = new HashMap<>();
        ArrayList<GeoLine> extendedGeoLines = new ArrayList<>();

        for (GeoLine geoLine : geoLines) {
//            ArrayList<GeoLine> dividedLines = divideAndUnify(geoLine);

            GeoPoint middlePoint = getMiddlePoint(new GeoPoint(geoLine.getStartX(), geoLine.getStartY()),
                    new GeoPoint(geoLine.getEndX(), geoLine.getEndY()));

            geoLinesToGeoAreas.put(geoLine, returnAreaAndCountry(middlePoint));
        }

        return geoLinesToGeoAreas;
    }

    private static double calculateDistance(GeoPoint point1, GeoPoint point2) {
        double distance = Math.hypot(point1.getXCoordinate()-point2.getYCoordinate(),
                                     point1.getYCoordinate()-point2.getYCoordinate());
        return distance;
    }

    public static GeoArea returnAreaAndCountry(GeoPoint point) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        String area = WebService.ocean(point.getXCoordinate(), point.getYCoordinate());

        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(point.getXCoordinate(), point.getYCoordinate());
        String country = nearbyPlaceName.get(0).getCountryName();

        double countryXCoordinate = nearbyPlaceName.get(0).getLatitude();
        double countryYCoordinate = nearbyPlaceName.get(0).getLongitude();
        GeoPoint countryGeoPoint = new GeoPoint(countryXCoordinate, countryYCoordinate);
        double distance = calculateDistance(point, countryGeoPoint);

        GeoArea geoArea = new GeoArea(area, country);

        return geoArea;
    }
}
