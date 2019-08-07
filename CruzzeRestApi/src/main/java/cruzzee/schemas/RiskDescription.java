package cruzzee.schemas;

public class RiskDescription {
    private int dangerLevel;
    private String info;

    public RiskDescription(int dangerLevel, String info) {
        this.dangerLevel = dangerLevel;
        this.info = info;
    }

    public int getDangerLevel() {
        return dangerLevel;
    }

    public void setDangerLevel(int dangerLevel) {
        this.dangerLevel = dangerLevel;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
