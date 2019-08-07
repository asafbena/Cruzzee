package cruzzee;

import cruzzee.schemas.GeoPoint;
import org.geonames.*;

import java.util.ArrayList;
import java.util.List;

public class CoordinateToArea {

    public static ArrayList<String> calculateAreaFromCoordinates(GeoPoint point) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        String area = WebService.ocean(point.getXCoordinate(), point.getYCoordinate());
        List<Toponym> nearbyPlaceName = WebService.findNearbyPlaceName(point.getXCoordinate(), point.getYCoordinate());
//        String country = nearbyPlaceName.getCountry();

        ArrayList<String> answer = new ArrayList<>();
        answer.add(area);
//        answer.add(country);

        //        WeatherObservation weatherObservation = WebService.findNearByWeather(point.getXCoordinate(), point.getYCoordinate());
        //        System.out.println(weatherObservation.getTemperature());
        //        System.out.println("The area is : " + area);

        return answer;
    }
}
