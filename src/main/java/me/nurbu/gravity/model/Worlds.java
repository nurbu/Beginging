package me.nurbu.gravity.model;

import java.util.List;


public class Worlds {
    private String worldName;
    private List<Regions> regions;

    public void setWorld(String world) {
        this.worldName = world;
    }

    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }

    public String getWorldName() {
        return worldName;
    }

    public List<Regions> getRegions() {
        return regions;
    }
}