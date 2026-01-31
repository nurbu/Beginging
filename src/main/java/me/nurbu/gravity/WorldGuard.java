package me.nurbu.gravity;

import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class WorldGuard {
    private final Set<RegionInfo> regions = new HashSet<>();
    Player player;

    public WorldGuard(Player player) {
        this.player = player;
    }


}
