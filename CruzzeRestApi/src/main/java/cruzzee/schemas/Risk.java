package cruzzee.schemas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Risk {
    private GeoLine line;
    private RiskDescription riskDescList;

    public GeoLine getLine() {
        return line;
    }

    public void setLine(GeoLine line) {
        this.line = line;
    }

    public RiskDescription getRiskDesc() {
        return riskDescList;
    }

    public void setRiskDesc(RiskDescription riskDesc) {
        this.riskDescList = riskDesc;
    }

    public Risk(GeoLine line, RiskDescription riskDesc) {
        this.line = line;
        this.riskDescList = riskDesc;
    }
}
