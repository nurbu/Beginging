package me.nurbu.gravity;

import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;


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
}
