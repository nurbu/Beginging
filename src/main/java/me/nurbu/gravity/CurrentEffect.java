package me.nurbu.gravity;

import me.nurbu.gravity.model.GravityEffect;

import java.util.Map;
import java.util.UUID;

public class CurrentEffect {
    private final Map<UUID, GravityEffect> effect;
    private final UUID id;

    public CurrentEffect(Map<UUID, GravityEffect> effect, UUID id) {
        this.effect = effect;
        this.id = id;
    }

    public double getEffect() {
        return effect.get(id).getGravityLevel();
    }
}
