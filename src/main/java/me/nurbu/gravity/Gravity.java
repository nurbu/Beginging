package me.nurbu.gravity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.nurbu.gravity.commands.CheckCommand;
import me.nurbu.gravity.model.GravityEffect;
import me.nurbu.gravity.model.PlanetData;
import me.nurbu.gravity.region.RegionInfo;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class Gravity extends JavaPlugin {
    // Database for every active user on server with UUID and World and RegionInfo.
    private final Map<UUID, World> playerWorlds = new HashMap<>();
    private final Map<UUID, RegionInfo> playerRegions = new HashMap<>();
    private final Map<UUID, GravityEffect> playerGravity = new HashMap<>();

    @Override
    public void onEnable() {
        RegionContainer WGC = WorldGuard.getInstance().getPlatform().getRegionContainer();
        saveResource("Gravitys.json", false);
        try {
            File file = new File(getDataFolder(), "Gravitys.json");
            ObjectMapper mapper = new ObjectMapper();
            PlanetData data = mapper.readValue(file, PlanetData.class);
            getServer().getPluginManager().registerEvents(new Listener(playerRegions, playerWorlds, WGC), this);
            getCommand("checkRegion").setExecutor(new CheckCommand(playerRegions, playerWorlds, playerGravity));
            GravityModifier gravityModifier = new GravityModifier(playerRegions, playerWorlds, data, playerGravity);
            getServer().getScheduler().runTaskTimer(this, gravityModifier::tick, 1L, 1L);
        } catch (IOException e) {
            getLogger().severe("Failed to Load Gravitys.json!! Gravity will not be applied");
            getLogger().severe(e.getMessage());
        }


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
