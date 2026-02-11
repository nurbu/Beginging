package me.nurbu.gravity;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class Gravity extends JavaPlugin {
    private final Map<UUID, World> playerWorlds = new HashMap<>();
    private final Map<UUID, RegionInfo> playerRegions = new HashMap<>();

    @Override
    public void onEnable() {
        RegionContainer WGC = WorldGuard.getInstance().getPlatform().getRegionContainer();
        getServer().getPluginManager().registerEvents(new Listener(playerRegions, playerWorlds, WGC), this);
        getCommand("checkRegion").setExecutor(new CheckCommand(playerRegions, playerWorlds));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
