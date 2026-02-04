package me.nurbu.gravity;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerContext {
    Player player;
    private final UUID PlayerId = player.getUniqueId();
    RegionContainer WGC;

    public PlayerContext(Player player, RegionContainer WGC) {
        this.player = player;
        this.WGC = WGC;
    }

    public void initaladd() {
        Location loc = player.getLocation();
        World world = player.getWorld();
        BlockVector3 wePos = BukkitAdapter.asBlockVector(loc);
        RegionManager regions = WGC.get(BukkitAdapter.adapt(world));
        ApplicableRegionSet set = regions.getApplicableRegions(wePos);
        ProtectedRegion current = null;
        for (ProtectedRegion region : set) {
            if (current == null || current.getPriority() < region.getPriority()) {
                current = region;
            }
            RegionInfo PlayerCuRegion = new RegionInfo(current.getId(), current.getPriority());
            RegionHolder held = new RegionHolder(PlayerId, world, PlayerCuRegion);
            held.addRegion();
        }
    }
}
