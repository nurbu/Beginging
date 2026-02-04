package me.nurbu.gravity;

import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegionHolder {
    private Map<UUID, ProtectedRegion> worlds = new HashMap<>();
    private Map<UUID, RegionInfo> Regions = new HashMap<>();
    private final UUID id;
    private final World world;
    private final RegionInfo region;

    public RegionHolder(UUID id, World world, RegionInfo region) {
        this.id = id;
        this.world = world;
        this.region = region;
    }

    public void addRegion() {
        
    }


}
