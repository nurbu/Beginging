package me.nurbu.gravity;

import org.bukkit.plugin.java.JavaPlugin;

public final class Gravity extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
