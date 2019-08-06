import org.geonames.*;

public class Main {

    public static void main(String[] args) throws Exception {
        WebService.setUserName("asafcruzzee"); // add your username here

//        ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
//        searchCriteria.setQ("tel-aviv");
//        ToponymSearchResult searchResult = WebService.search(searchCriteria);
        String area = WebService.ocean(32.008044,34.406952);
        WeatherObservation weatherObservation = WebService.findNearByWeather(32.008044,34.406952);
        System.out.println(weatherObservation.getTemperature());
        System.out.println("The area is : " + area);
//        for (Toponym toponym : searchResult.getToponyms()) {
//            System.out.println(toponym.getName()+" "+ toponym.getCountryName());
//        }
    }
}
