package cruzzee;


import cruzzee.schemas.GeoLine;

import java.util.HashMap;

class SearchResults{
    HashMap<String, String> relevantHeaders;
    String jsonResponse;
    GeoLine pageArea;
    int occurrence;
    int consequences;
    int totalRisk;


    SearchResults(HashMap<String, String> headers, String json) {
        relevantHeaders = headers;
        jsonResponse = json;
    }

    public void calcRisk(){
        //consequences - Check for amount of appearances of words combination
        //for every page risk calculation U send the address to pazit and inbar api to calculate reliability of source
        //the result returned from pazit and inber api should be a percent of trust between 0 to 1.
        //occurrence - Check the distance from departure date to when article published
    }
}
