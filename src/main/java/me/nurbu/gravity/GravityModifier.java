package me.nurbu.gravity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;

public class GravityModifier {
    private final Map<UUID, Integer> playerTick;

    public GravityModifier(Map<UUID, Integer> playerTick) {
        this.playerTick = playerTick;
    }

    public void Tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            UUID id = player.getUniqueId();
            if (player.isOnGround()) {
                playerTick.put(id, 0);
            }
            int ticks = playerTick.get(id);
            ticks++;
            playerTick.put(id, ticks);

            if (ticks >= 2) {
                Vector vel = player.getVelocity();
                double gravityUp = 0.04;
                double gravityDown = 0.06;
                if (vel.getY() > 0) {
                    vel.setY(vel.getY() + gravityUp);
                } else {
                    vel.setY(vel.getY() + gravityDown);
                }
                if (vel.getY() < -4.5) {
                    vel.setY(-4.5);
                }
                player.setVelocity(vel);
            }
        }
    }
}
