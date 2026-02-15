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

import java.util.Map;
import java.util.UUID;

// Makes sure Player info Region and world Info is up to date.
public class PlayerContext {
    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<UUID, World> playerWorlds;
    private final Player player;
    private final RegionContainer WGC;

    public PlayerContext(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, Player player, RegionContainer WGC) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.player = player;
        this.WGC = WGC;
    }

    //Checks to see if player moves and not just turns head.
    public void movementCheck(Location to, Location from) {
        boolean playerMoved;
        if (to != null) {
            playerMoved = !(to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockY() == from.getBlockY());
        } else {
            playerMoved = false;
        }

        if (playerMoved) {
            checkCuLocation();
        }

    }

    // If Player tps at all check new Region by calling checkCuLocation().
    public void tpCheck(Location to) {
        if (to != null) {
            checkCuLocation();
        }
    }

    /*
    Main Region Checker/Updater.
    Checks if there are any regions in that world.
    ((If Yes then checks if that player's coordinates has regions in it.
    If Yes then loop to check highest priority if priority tied checks to see which one is the child between the two tied.
    If No then just stores the RegionInfo object as Id: Global with priority: 0. Then stores world as regular.)
    If players coordinates has no regions within it also say Id: Global with Priority: 0.)
    If world has no regions as at all meaning WorldGuard didn't create any regions again stores as using Global gravity.
    */
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

    //Accesses the Databases and fully updates them as well.
    /*
    Future Implementation
    * Add gravity update too.
    */
    private void updateLocation(UUID playerId, World world, RegionInfo playerCuRegion) {
        playerRegions.put(playerId, playerCuRegion);
        playerWorlds.put(playerId, world);
        /*if (playerRegions.containsKey(playerId)) {
            if (playerRegions.get(playerId).equals(playerCuRegion)) {
                playerRegions.put(playerId, playerCuRegion);
                playerWorlds.put(playerId, world);
            }

        } else {
            playerRegions.put(playerId, playerCuRegion);
        }
        */



        /*activate the gravity checker to see which gravity should apply to this person now.*/
    }

}
