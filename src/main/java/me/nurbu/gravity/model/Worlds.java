package me.nurbu.gravity.model;

import java.util.List;


public class Worlds {
    private String world;
    private List<Regions> regions;

    public void setWorld(String world) {
        this.world = world;
    }

    public void setRegions(List<Regions> regions) {
        this.regions = regions;
    }

    public String getWorld() {
        return world;
    }

    public List<Regions> getRegions() {
        return regions;
    }
}