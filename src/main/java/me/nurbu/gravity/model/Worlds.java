package me.nurbu.gravity.model;

import java.util.List;


public class Worlds {
    private final String world;
    private final List<Regions> regions;

    public Worlds(String world, List<Regions> regions) {
        this.world = world;
        this.regions = regions;
    }

    public String getWorld() {
        return world;
    }

    public List<Regions> getRegions() {
        return regions;
    }
}