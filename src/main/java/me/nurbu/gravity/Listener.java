package me.nurbu.gravity;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class Listener implements org.bukkit.event.Listener {

    private final Set<UUID> specialPlayers = new HashSet<>();
    UUID b = UUID.fromString("bba02a1c-6a48-42fa-986c-6f6be7fd28ca");


    @EventHandler
    public void PlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID id = player.getUniqueId();
        if (id.equals(b)) {
            player.sendActionBar("Welcome back baby");
        }


    }
}
