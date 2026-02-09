package me.nurbu.gravity;

import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

import java.util.UUID;


public class Listener implements org.bukkit.event.Listener {

    RegionContainer WGC;

    public Listener(RegionContainer WGC) {
        this.WGC = WGC;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PlayerContext Tracker = new PlayerContext(player, WGC);
        Tracker.initialAdd();
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
