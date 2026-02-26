package me.nurbu.gravity;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.UUID;

public class GravityModifier {

    private final double Vanilla_Gravity = 0.08;
    private final double Vanilla_Air_Friction = 0.91;
    private final double Moon_Gravity = 0.013;
    private final double Max_Fall_speed = -1.2;

    public void Tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {

            UUID id = player.getUniqueId();
            Vector vel = player.getVelocity();

            if (!player.isOnGround()) {

                vel.setY(vel.getY() + Vanilla_Gravity - Moon_Gravity);

                vel.setX(vel.getX() / Vanilla_Air_Friction);
                vel.setZ(vel.getZ() / Vanilla_Air_Friction);

                // --- Fall velocity Stopper ---
                if (vel.getY() < Max_Fall_speed) {
                    vel.setY(Max_Fall_speed);
                }
                player.setVelocity(vel);

            }
        }
    }
}