package me.nurbu.gravity.commands;

import me.nurbu.gravity.model.GravityEffect;
import me.nurbu.gravity.region.RegionInfo;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

// Simple Command to check if Location in Databases are being updated properly when movement occurs.
// Such as walking and tping.
public class CheckCommand implements CommandExecutor, TabExecutor {
    private final Map<UUID, RegionInfo> playerRegions;
    private final Map<UUID, World> playerWorlds;
    private final Map<UUID, GravityEffect> playerGravity;

    public CheckCommand(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> worlds, Map<UUID, GravityEffect> gravity) {
        this.playerRegions = playerRegions;
        this.playerWorlds = worlds;
        this.playerGravity = gravity;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player player)) {
            commandSender.sendMessage("Only players can use this command");
            return true;
        }

        UUID id = player.getUniqueId();
        RegionInfo regionInfo = playerRegions.get(id);
        World world = playerWorlds.get(id);
        GravityEffect effect = playerGravity.get(id);

        String regionDisplay = (regionInfo != null) ? regionInfo.getId() : "Not tracked yet";
        String worldDisplay = (world != null) ? world.getName() : "Not tracked yet";
        String gravityDisplay = (effect != null)
                ? "Gravity: " + effect.getGravityLevel() + " MaxFall: " + effect.getMaxFallSpeed()
                : "No active effect";

        player.sendMessage("Region: " + regionDisplay + " | World: " + worldDisplay + " | " + gravityDisplay);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return new ArrayList<>();
    }
}
