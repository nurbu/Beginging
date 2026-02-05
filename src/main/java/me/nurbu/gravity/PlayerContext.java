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
    RegionContainer WGC;

    public PlayerContext(Player player, RegionContainer WGC) {
        this.player = player;
        this.WGC = WGC;
    }

    public void initialAdd() {
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
                    if (current.getParent() == null) current = region;
                }

            }
            String id = current.getId();
            int priority = current.getPriority();
            playerCuRegion = new RegionInfo(id, priority);

        } else {
            playerCuRegion = new RegionInfo("Global", 0);
        }

        RegionHolder held = new RegionHolder(playerId, world, playerCuRegion);
        held.addRegion();
        held.addWorld();

    }

}
