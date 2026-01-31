package me.nurbu.gravity;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Listener implements org.bukkit.event.Listener {

    private final Set<UUID> specialPlayers = new HashSet<>();
    UUID b = UUID.fromString("bba02a1c-6a48-42fa-986c-6f6be7fd28ca");
    RegionContainer WGC;

    public Listener(RegionContainer WGC) {
        this.WGC = WGC;
    }

    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        Location loc = player.getLocation();
        World world = loc.getWorld();
        RegionManager regions = WGC.get(BukkitAdapter.adapt(world));
        if (regions != null) {
            for (RegionInfo r : regions) {
                RegionInfo tempre = new RegionInfo(reg);
            }
        }
        UUID id = player.getUniqueId();
        if (id.equals(b)) {
            player.sendActionBar("Welcome back baby" + world + loc);
        }


    }
}
