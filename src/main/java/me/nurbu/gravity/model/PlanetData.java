package me.nurbu.gravity.model;

import java.util.List;


public class PlanetData {
    private final List<Worlds> planets;

    public PlanetData(List<Worlds> planets) {
        this.planets = planets;
    }

    public List<Worlds> getPlanets() {
        return planets;
    }
}
