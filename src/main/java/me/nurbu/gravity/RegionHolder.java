package me.nurbu.gravity;

import org.bukkit.World;

import java.util.Map;
import java.util.UUID;

public class RegionHolder {
    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<UUID, World> playerWorlds;


    public RegionHolder(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;

    }

    public String getRegion(UUID id) {
        RegionInfo val = playerRegions.get(id);
        return val.getId();
    }

    public String getWorld(UUID id) {
        World val = playerWorlds.get(id);
        return val.getName();
    }

}
