package me.nurbu.gravity;

import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.Map;
import java.util.UUID;


public class Listener implements org.bukkit.event.Listener {

    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<UUID, World> playerWorlds;
    private final RegionContainer WGC;

    public Listener(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, RegionContainer WGC) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.WGC = WGC;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerContext Tracker = new PlayerContext(playerRegions, playerWorlds, player, WGC);
        Tracker.checkCuLocation();
    }

    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        PlayerLocationComparator pl = new PlayerLocationComparator(player, to, from);
        boolean moveStatus = pl.locationCheck();
        if (!moveStatus) pl.updateRegion();
    }

    public void onTP(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        UUID id = player.getUniqueId();

    }
}
