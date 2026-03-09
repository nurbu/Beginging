package me.nurbu.gravity.commands;

import me.nurbu.gravity.CurrentEffect;
import me.nurbu.gravity.model.GravityEffect;
import me.nurbu.gravity.region.RegionHolder;
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
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage("Only real ggs can use this command AI looking ah");
            return true;
        }
        Player player = (Player) commandSender;
        UUID id = player.getUniqueId();
        RegionHolder held = new RegionHolder(playerRegions, playerWorlds);
        CurrentEffect gravity = new CurrentEffect(playerGravity, id);
        double CG = gravity.getEffect();
        String R = held.getRegion(id);
        String W = held.getWorld(id);

        commandSender.sendMessage(R + " " + W + " " + CG);
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] strings) {
        return new ArrayList<>();
    }
}
