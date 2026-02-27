package me.nurbu.gravity;

import me.nurbu.gravity.model.GravityEffect;
import me.nurbu.gravity.model.PlanetData;
import me.nurbu.gravity.model.Regions;
import me.nurbu.gravity.model.Worlds;
import me.nurbu.gravity.region.RegionInfo;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.Map;
import java.util.UUID;

public class GravityModifier {

    private final double Vanilla_Gravity = 0.08;
    private final double Vanilla_Air_Friction = 0.91;

    private final Map<UUID, World> playerWorlds;
    private final Map<UUID, RegionInfo> playerRegions;
    private final PlanetData data;

    public GravityModifier(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, PlanetData data) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.data = data;
    }

    public void Tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOnGround()) {
                UUID id = player.getUniqueId();
                Vector vel = player.getVelocity();

                GravityEffect effect = getGravityEffect(id);
                applyGravity(vel, effect);

                player.setVelocity(vel);
            }
        }
    }

    private GravityEffect getGravityEffect(UUID id) {
        RegionInfo regionInfo = playerRegions.get(id);
        World world = playerWorlds.get(id);

        // If no region or world data found, return null to use vanilla gravity
        if (regionInfo == null || world == null) return null;

        // Global region = pure vanilla, no modification
        if (regionInfo.getId().equals("Global")) return null;

        // Find the matching world in planetData
        for (Worlds w : data.getPlanets()) {
            if (w.getWorld().equals(world.getName())) {

                // Find the matching region
                for (Regions region : w.getRegions()) {
                    if (region.getRegionName().equals(regionInfo.getId())) {

                        // Determine time period
                        long time = world.getTime();
                        if (time < 8000) {
                            return region.getTimes().getMorning();
                        } else if (time < 16000) {
                            return region.getTimes().getAfternoon();
                        } else {
                            return region.getTimes().getNight();
                        }
                    }
                }
            }
        }

        // World or region not found in JSON, fall back to vanilla
        return null;
    }

    private void applyGravity(Vector vel, GravityEffect effect) {
        // If effect is null use pure vanilla gravity
        double gravityLevel = (effect != null) ? effect.getGravityLevel() : Vanilla_Gravity;
        double maxFallSpeed = (effect != null) ? effect.getMaxFallSpeed() : -3.92;

        vel.setY(vel.getY() - gravityLevel);
        vel.setX(vel.getX() / Vanilla_Air_Friction);
        vel.setZ(vel.getZ() / Vanilla_Air_Friction);

        if (vel.getY() < maxFallSpeed) {
            vel.setY(maxFallSpeed);
        }
    }
}