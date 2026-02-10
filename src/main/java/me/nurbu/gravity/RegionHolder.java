package me.nurbu.gravity;

import org.bukkit.World;

import java.util.Map;
import java.util.UUID;

public class RegionHolder {
    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<UUID, World> playerWorlds;
    private final UUID id;
    private final World world;
    private final RegionInfo region;


    public RegionHolder(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, UUID id, World world, RegionInfo region) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.id = id;
        this.world = world;
        this.region = region;
    }


    public String getRegion(UUID goddid) {
        RegionInfo val = playerRegions.get(goddid);
        return val.getId();
    }

    public void addRegion() {
        playerRegions.put(id, region);
    }

    public void addWorld() {
        playerWorlds.put(id, world);
    }


}
