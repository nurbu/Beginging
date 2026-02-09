package me.nurbu.gravity;

import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.plugin.java.JavaPlugin;

private RegionContainer WGC;

public final class Gravity extends JavaPlugin {

    @Override
    public void onEnable() {
        WGC = WorldGuard.getInstance().getPlatform().getRegionContainer();

        getServer().getPluginManager().registerEvents(new Listener(WGC), this);
        getCommand("checkRegion").setExecutor(new CheckCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
