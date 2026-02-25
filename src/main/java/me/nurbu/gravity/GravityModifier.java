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
                int normalTickTime = 10;
                double normalAirDrag = 0.91;
                if (vel.getY() > 0) {
                    int totalTick = (int) Math.floor(0.42 / (0.08 + gravityUp));
                    double result = Math.pow(0.91, (double) normalTickTime / totalTick);
                    vel.setX(vel.getX() * (result / normalAirDrag));
                    vel.setZ(vel.getZ() * (result / normalAirDrag));
                    vel.setY(vel.getY() + gravityUp);
                } else {
                    int totalTick = (int) Math.floor(0.42 / (0.08 + gravityDown));
                    double result = Math.pow(0.91, (double) normalTickTime / totalTick);
                    vel.setX(vel.getX() * (result / normalAirDrag));
                    vel.setZ(vel.getZ() * (result / normalAirDrag));
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
