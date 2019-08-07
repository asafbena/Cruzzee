package cruzzee;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cruzzee.Application.prettify;

public final class DangerAnalyzer {

    public static Map<String, Integer> getAreaToDanger(List<String> areas) {
        Map<String, Integer> areaToDanger = new HashMap<>();

        for (String area : areas) {
            areaToDanger.put(area, getDangerForArea(area));
        }

        return areaToDanger;
    }

    private static Integer getDangerForArea(String area) {
        SearchResults result = null;
        System.out.println("Analyzing Area " + area);
        try {
            result = ContentDownloader.SearchWeb(getSearchQuery(area));
        } catch (Exception e) {
            System.out.println("Error");
        }
        System.out.println("\nJSON Response:\n");
        System.out.println(prettify(result.jsonResponse));
        return 0;
    }

    private static String getSearchQuery(String area) {

        String concat = area.replace(' ', '+').concat("+pirate+attack");
        System.out.println("Searching query" + concat);
        return concat;
    }
}
