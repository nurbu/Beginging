package me.nurbu.gravity;

import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegionHolder {
    private final Map<UUID, World> worlds = new HashMap<>();
    private final static Map<UUID, RegionInfo> Regions = new HashMap<>();
    private final UUID id;
    private final World world;
    private final RegionInfo region;


    public RegionHolder(UUID id, World world, RegionInfo region) {
        this.id = id;
        this.world = world;
        this.region = region;
    }


    public String getRegion(UUID goddid) {
        RegionInfo val = Regions.get(goddid);
        return val.getId();
    }

    public void addRegion() {
        Regions.put(id, region);
    }

    public void addWorld() {
        worlds.put(id, world);
    }


}
