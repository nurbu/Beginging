package me.nurbu.gravity;

import me.nurbu.gravity.model.GravityEffect;
import me.nurbu.gravity.model.PlanetData;
import me.nurbu.gravity.model.Regions;
import me.nurbu.gravity.model.Worlds;
import me.nurbu.gravity.region.RegionInfo;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class GravityModifier {

    private final Map<UUID, World> playerWorlds;
    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<String, Map<String, Regions>> regionLookup = new HashMap<>();
    private final Map<UUID, GravityEffect> playerGravity;

    public GravityModifier(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> playerWorlds, PlanetData data, Map<UUID, GravityEffect> playerGravity) {
        this.playerRegions = playerRegions;
        this.playerWorlds = playerWorlds;
        this.playerGravity = playerGravity;
        buildLookup(data);
    }

    public void buildLookup(PlanetData data) {
        for (Worlds world : data.getPlanets()) {
            Map<String, Regions> regionMap = new HashMap<>();
            for (Regions region : world.getRegions()) {
                regionMap.put(region.getRegionName(), region);
            }
            regionLookup.put(world.getWorldName(), regionMap);
        }

    }

    public void tick() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            if (!player.isOnGround()) {
                UUID id = player.getUniqueId();

                if (player.isFlying() || player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR)
                    continue;
                Vector vel = player.getVelocity();
                RegionInfo regionInfo = playerRegions.get(id);
                if (regionInfo == null || regionInfo.getId().equals("Global")) continue;
                GravityEffect effect = getGravityEffect(id);
                if (effect == null) continue;


                applyGravity(id, vel, effect);

                player.setVelocity(vel);
            }
        }
    }

    private GravityEffect getGravityEffect(UUID id) {
        RegionInfo regionInfo = playerRegions.get(id);
        World world = playerWorlds.get(id);

        if (regionInfo == null || world == null) return null;

        Map<String, Regions> worldRegions = regionLookup.get(world.getName());
        if (worldRegions == null) {
            return null;
        }
        Regions region = worldRegions.get(regionInfo.getId());
        if (region == null) {
            return null;
        }
        long time = world.getTime();
        if (time < 8000) {
            return region.getTimes().getMorning();
        } else if (time < 16000) {
            return region.getTimes().getAfternoon();
        } else {
            return region.getTimes().getNighttime();
        }

    }

    private void applyGravity(UUID id, Vector vel, GravityEffect effect) {
        playerGravity.put(id, effect);
        // If effect is null use pure vanilla gravity
        double Vanilla_Air_Friction = 0.91;
        double gravityLevel = effect.getGravityLevel();
        double maxFallSpeed = effect.getMaxFallSpeed();

        vel.setY(vel.getY() + gravityLevel);
        vel.setX(vel.getX() / Vanilla_Air_Friction);
        vel.setZ(vel.getZ() / Vanilla_Air_Friction);

        if (vel.getY() < maxFallSpeed) {
            vel.setY(maxFallSpeed);
        }
    }
}