package cruzzee;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.util.StringUtils;

import java.util.*;

public final class DangerAnalyzer {

    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    public static Map<String, Integer> getAreaToDanger(List<String> areas) {
        Map<String, Integer> areaToDanger = new HashMap<>();

        for (String area : areas) {
            areaToDanger.put(area, getDangerForArea(area, "iran"));
        }

        return areaToDanger;
    }

    public static Integer getDangerForArea(String area, String country) {
        long overallRisk = 0;
        System.out.println("Analyzing Area " + area);
        for (Constants.Dangers danger : Constants.dangersKeyWords.keySet()) {
            System.out.println("Checking for risk: " + danger.toString());
            Multimap<Integer, String> headersSortedByDanger = MultimapBuilder.treeKeys().linkedListValues().build();
            SearchResults result;
            JsonArray resultJson = null;
            try {
                result = ContentDownloader.SearchWeb(getSearchQuery(country, area, danger));
                resultJson = PARSER.parse(result.jsonResponse).getAsJsonObject().getAsJsonArray("value");
            } catch (Exception e) {
                System.out.println("Error");
            }

            if (resultJson == null) {
                System.out.println("Failed to pull headline, Retrying");
                return getDangerForArea(area, country);
            }

            for (JsonElement headline : resultJson) {
                String headlineText = String.valueOf(headline.getAsJsonObject().get("description"));
                int relevance = getHeaderRelevance(headlineText, area, country);
                if (relevance > 0) {
                    headersSortedByDanger.put(relevance, headlineText);
                }
            }

            System.out.println("Pulled " + resultJson.size() + " Headlines");
            int maxRelevance = headersSortedByDanger.keys().stream().max(Integer::compare).get();
            List<String> relevantHeaders = (List<String>) headersSortedByDanger.asMap().get(maxRelevance);

            System.out.println("Found " + relevantHeaders.size() + " Relevant Headlines");

            int estimatedRisk = 0;

            if (relevantHeaders.size() > 0) {
                for (String relevantHeader : relevantHeaders) {
                    estimatedRisk += estimateRisk(danger, relevantHeader);
                }

                overallRisk += estimatedRisk / relevantHeaders.size();
            }
        }
        return 0;
    }

    private static int getHeaderRelevance(String header, String area, String country) {
        int occurrences = 0;
        List<String> keywords = new ArrayList<String>(Arrays.asList(area.split("\\s+")));
        header = header.toLowerCase();
        keywords.removeIf(o -> {
            return o.equals("of") || o.equals("a") || o.equals("gulf") || o.equals("sea");
        });

        if (header.contains(country.toLowerCase())) {
            for (String keyword : keywords) {
                occurrences += StringUtils.countOccurrencesOf(header, keyword.toLowerCase());
            }
        }

        return occurrences;
    }

    private static String getSearchQuery(String country, String area, Constants.Dangers danger) {
        String concat = country.replace(' ', '+') + "+" + area.replace(' ', '+').concat(Constants.dangersSearchQuery.get(danger));
        System.out.println("Searching query" + concat);
        return concat;
    }

    private static Integer estimateRisk(Constants.Dangers danger, String articleDescription) {
        List<String> dangerKeyWords = Constants.dangersKeyWords.get(danger);
        int countKeyWords = 0;

        for (String keyWord : dangerKeyWords) {
            if (articleDescription.contains(keyWord))
                countKeyWords++;
        }

        if (countKeyWords < 2)
            return 0;
        return 1;
    }
}