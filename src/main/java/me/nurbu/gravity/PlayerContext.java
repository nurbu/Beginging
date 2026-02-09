package me.nurbu.gravity;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.Regions;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerContext {
    private final Player player;
    private final RegionContainer WGC;

    public PlayerContext(Player player, RegionContainer WGC) {
        this.player = player;
        this.WGC = WGC;
    }

    public void checkCuLocation() {
        Location loc = player.getLocation();
        World world = player.getWorld();
        UUID playerId = player.getUniqueId();
        BlockVector3 wePos = BukkitAdapter.asBlockVector(loc);
        RegionManager regions = WGC.get(BukkitAdapter.adapt(world));
        RegionInfo playerCuRegion;
        if (regions != null) {
            ApplicableRegionSet set = regions.getApplicableRegions(wePos);
            ProtectedRegion current = null;
            for (ProtectedRegion region : set) {
                if (current == null || current.getPriority() < region.getPriority()) {
                    current = region;
                } else if (current.getPriority() == region.getPriority()) {
                    if (region.getParent() == current) current = region;
                }

            }
            if (current != null) {
                String id = current.getId();
                int priority = current.getPriority();
                playerCuRegion = new RegionInfo(id, priority);
            } else playerCuRegion = new RegionInfo("Global", 0);
        } else {
            playerCuRegion = new RegionInfo("Global", 0);
        }
        updateLocation(playerId, world, playerCuRegion);
    }

    public void updateLocation(UUID playerId, World world, RegionInfo playerCuRegion) {
        RegionHolder held = new RegionHolder(playerId, world, playerCuRegion);
        if (Regions.contains)
    }

}
