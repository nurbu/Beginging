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

    private final Map<UUID, World> playerWorlds;
    private final Map<UUID, RegionInfo> playerRegions;
    private final PlanetData data;
    private final Map<UUID, GravityEffect> playerGravity;

    public GravityModifier(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, PlanetData data, Map<UUID, GravityEffect> playerGravity) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.data = data;
        this.playerGravity = playerGravity;
    }

    public void Tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOnGround()) {
                UUID id = player.getUniqueId();
                Vector vel = player.getVelocity();

                GravityEffect effect = getGravityEffect(id);
                playerGravity.put(id, effect);
                applyGravity(vel, effect);

                player.setVelocity(vel);
            }
        }
    }

    private GravityEffect getGravityEffect(UUID id) {
        RegionInfo regionInfo = playerRegions.get(id);
        World world = playerWorlds.get(id);

        if (regionInfo == null || world == null) return null;

        if (regionInfo.getId().equals("Global")) return null;

        for (Worlds w : data.getPlanets()) {
            if (w.getWorld().equals(world.getName())) {

                for (Regions region : w.getRegions()) {
                    if (region.getRegionName().equals(regionInfo.getId())) {

                        long time = world.getTime();
                        if (time < 8000) {
                            return region.getTimes().getMorning();
                        } else if (time < 16000) {
                            return region.getTimes().getAfternoon();
                        } else {
                            return region.getTimes().getNighttime();
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
        double Vanilla_Air_Friction = 0.91;
        double gravityLevel = (effect != null) ? effect.getGravityLevel() : 0;
        double maxFallSpeed = (effect != null) ? effect.getMaxFallSpeed() : -3.92;

        vel.setY(vel.getY() + gravityLevel);
        vel.setX(vel.getX() / Vanilla_Air_Friction);
        vel.setZ(vel.getZ() / Vanilla_Air_Friction);

        if (vel.getY() < maxFallSpeed) {
            vel.setY(maxFallSpeed);
        }
    }
}