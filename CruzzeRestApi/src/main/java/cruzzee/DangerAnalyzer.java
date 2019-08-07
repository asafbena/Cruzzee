package cruzzee;

import com.google.common.collect.Multimap;
import com.google.common.collect.MultimapBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import cruzzee.schemas.RiskDescription;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class DangerAnalyzer {

    private static final Gson GSON = new Gson();
    private static final JsonParser PARSER = new JsonParser();

    public static RiskDescription getDangerForArea(String area, String country, String cargo) {
        int overallRisk = 0;
        List<RiskDescription> tempRiskDescriptions = new ArrayList();
//        RiskDescription riskDescriptions = new ArrayList();

        System.out.println("Analyzing Area " + area);
        for (Constants.Dangers danger : Constants.dangersKeyWords.keySet()) {
            System.out.println("Checking for risk: " + danger.toString());
            Multimap<Integer, String> headersSortedByDanger = MultimapBuilder.treeKeys().linkedListValues().build();
            SearchResults result;
            JsonArray resultJson = null;
            try {
                result = ContentDownloader.SearchWeb(getSearchQuery(country, area, danger, cargo));
                resultJson = PARSER.parse(result.jsonResponse).getAsJsonObject().getAsJsonArray("value");
            } catch (Exception e) {
                System.out.println("Error");
            }

            if (resultJson == null) {
                System.out.println("Failed to pull headline, Retrying");
                return getDangerForArea(area, country, cargo);
            }

            for (JsonElement headline : resultJson) {
                String headlineText = String.valueOf(headline.getAsJsonObject().get("description"));
                int relevance = getHeaderRelevance(headlineText, area, country);
                if (relevance > 0) {
                    headersSortedByDanger.put(relevance, headlineText);
                }
            }

            System.out.println("Pulled " + resultJson.size() + " Headlines");
            if (!headersSortedByDanger.isEmpty()) {
                int maxRelevance = headersSortedByDanger.keys().stream().max(Integer::compare).get();

                List<String> relevantHeaders = headersSortedByDanger.asMap().values().stream().
                        collect(ArrayList::new, List::addAll, List::addAll);

                System.out.println("Found " + relevantHeaders.size() + " Relevant Headlines");

                int estimatedRisk = 0;

                if (relevantHeaders.size() > 0) {
                    for (String relevantHeader : relevantHeaders) {
                        estimatedRisk += estimateRisk(danger, relevantHeader);
                    }

                    overallRisk += estimatedRisk;
                }

                if (country.isEmpty()) {
                    overallRisk /= 5;
                }

                tempRiskDescriptions.add(new RiskDescription(overallRisk, danger.toString().toLowerCase()));
            }
        }
        int danger = 0;
        String info = "";
        int i=0;
        for (RiskDescription riskDescription: tempRiskDescriptions
             ) {
            danger += riskDescription.getDangerLevel();
            if(i+1<tempRiskDescriptions.size())
                info = info + riskDescription.getInfo() + " & ";
            else
                info = info + riskDescription.getInfo();
        }
        danger = danger / tempRiskDescriptions.size();
        if(danger > 10)
            danger = 10;
//        riskDescriptions.add(new RiskDescription(danger, info));
        return new RiskDescription(danger, info);
    }

    private static int getHeaderRelevance(String header, String area, String country) {
        int occurrences = 0;
        List<String> keywords = new ArrayList<String>(Arrays.asList(area.split("\\s+")));
        header = header.toLowerCase();
        keywords.removeIf(o -> {
            return o.equals("of") || o.equals("a") || o.equals("sea");
        });

        if (header.contains(country.toLowerCase())) {
            for (String keyword : keywords) {
                occurrences += StringUtils.countOccurrencesOf(header, keyword.toLowerCase());
            }
        }

        return occurrences;
    }

    private static String getSearchQuery(String country, String area, Constants.Dangers danger, String cargo) {
        String concat = "";
        if (!country.isEmpty()) {
            concat = country.replace(' ', '+') + "+" + area.replace(' ', '+').concat(Constants.dangersSearchQuery.get(danger));
        } else {
            concat = area.replace(' ', '+').concat(Constants.dangersSearchQuery.get(danger));
        }
        if (danger == Constants.Dangers.ATTACK || danger == Constants.Dangers.PIRATE) {
            concat = concat.concat("+" + cargo.replace(' ', '+'));
        }

        System.out.println("Searching query " + concat);
        return concat;
    }

    private static Integer estimateRisk(Constants.Dangers danger, String articleDescription) {
        List<String> dangerKeyWords = Constants.dangersKeyWords.get(danger);
        int countKeyWords = 0;

        for (String keyWord : dangerKeyWords) {
            if (articleDescription.contains(keyWord))
                countKeyWords++;
        }

        if (countKeyWords < 1)
            return 0;
        return 1;
    }
}