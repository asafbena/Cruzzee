package cruzzee;

import org.geonames.Toponym;
import org.geonames.WeatherObservation;
import org.geonames.WebService;

import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here
        CoordinateToArea.getAreaToCoordinateStream(new ArrayList<>());

////        searchCriteria.setQ("tel-aviv");
////        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
////        ToponymSearchResult searchResult = WebService.search(searchCriteria);
////        String area = WebService.ocean(32.008044,34.406952);
////        List<Toponym> toponyms = WebService.findNearbyPlaceName(32.008044,34.406952);
//        String area = WebService.ocean(10.411528,107.092511);
//        List<Toponym> toponyms = WebService.findNearbyPlaceName(10.448184,106.944047);
////        List<Toponym> toponyms    = WebService.findNearby(10.448184,106.944047, FeatureClass.S, );
//        WeatherObservation weatherObservation = WebService.findNearByWeather(32.008044,34.406952);
//        System.out.println(weatherObservation.getTemperature());
//        System.out.println("The area is : " + area);
////        for (Toponym toponym : searchResult.getToponyms()) {
////            System.out.println(toponym.getName()+" "+ toponym.getCountryName());
////        }

    }
}
