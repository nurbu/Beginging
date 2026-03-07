package me.nurbu.gravity.model;

public class Regions {
    private String regionName;
    private TimeOfDay times;

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public void setTimes(TimeOfDay times) {
        this.times = times;
    }

    public String getRegionName() {
        return regionName;
    }

    public TimeOfDay getTimes() {
        return times;
    }
}
