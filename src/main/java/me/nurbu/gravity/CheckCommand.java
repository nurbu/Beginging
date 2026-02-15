package me.nurbu.gravity;

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

    public CheckCommand(Map<UUID, RegionInfo> playerRegions, Map<UUID, World> worlds) {
        this.playerRegions = playerRegions;
        this.playerWorlds = worlds;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only real ggs can use this command AI looking ah");
            return true;
        }
        Player player = (Player) commandSender;
        UUID id = player.getUniqueId();
        RegionHolder held = new RegionHolder(playerRegions, playerWorlds);
        String R = held.getRegion(id);
        String W = held.getWorld(id);
        commandSender.sendMessage(R + " " + W);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return new ArrayList<>();
    }
}
