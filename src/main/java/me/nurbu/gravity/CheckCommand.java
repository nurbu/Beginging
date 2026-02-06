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
import java.util.UUID;

public class CheckCommand implements CommandExecutor, TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only real ggs can use this command AI looking ah");
            return true;
        }
        Player player = (Player) commandSender;
        UUID id = player.getUniqueId();
        World world = player.getWorld();
        RegionInfo info = new RegionInfo("Earth", 5);
        RegionHolder held = new RegionHolder(id, world, info);
        String M = held.getRegion(id);
        commandSender.sendMessage(M);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return new ArrayList<>();
    }
}
