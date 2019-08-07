package schemas;

import java.util.HashMap;
import java.util.Map;

public class AreaRisks {
    //area -> (risks description-> risk numerical)
    private Map<GeoLine, Map<String, Integer>> risks;


    public AreaRisks() {
        this.risks = new HashMap<>();
    }

    public Map<GeoLine, Map<String, Integer>> getRisks() {
        return risks;
    }
}
