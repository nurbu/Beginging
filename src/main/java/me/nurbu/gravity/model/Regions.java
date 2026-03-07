package me.nurbu.gravity.model;

public class Regions {
    private final String regionName;
    private final TimeOfDay times;

    public Regions(String regionName, TimeOfDay times) {
        this.regionName = regionName;
        this.times = times;
    }

    public String getRegionName() {
        return regionName;
    }

    public TimeOfDay getTimes() {
        return times;
    }
}
