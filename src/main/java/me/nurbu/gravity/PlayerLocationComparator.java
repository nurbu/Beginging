package me.nurbu.gravity;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class PlayerLocationComparator {
    Player player;
    Location to, from;

    public PlayerLocationComparator(Player player, Location to, Location from) {
        this.player = player;
        this.to = to;
        this.from = from;
    }

    public boolean locationCheck() {
        if (to != null) {
            return (to.getBlockX() == from.getBlockX() && to.getBlockY() == from.getBlockY() && to.getBlockY() == from.getBlockY());
        }
        return true;
    }

    public void updateRegion() {

    }
}
