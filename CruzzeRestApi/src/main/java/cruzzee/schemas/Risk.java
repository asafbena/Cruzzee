package cruzzee.schemas;

import java.util.HashMap;
import java.util.Map;

public class Risk {
    private GeoLine line;
    private RiskDescription riskDesc;

    public GeoLine getLine() {
        return line;
    }

    public void setLine(GeoLine line) {
        this.line = line;
    }

    public RiskDescription getRiskDesc() {
        return riskDesc;
    }

    public void setRiskDesc(RiskDescription riskDesc) {
        this.riskDesc = riskDesc;
    }

    public Risk(GeoLine line, RiskDescription riskDesc) {
        this.line = line;
        this.riskDesc = riskDesc;
    }
}
