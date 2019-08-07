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
}
