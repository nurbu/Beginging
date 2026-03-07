package me.nurbu.gravity.model;

public class GravityEffect {
    private final double gravityLevel;
    private final double maxFallSpeed;

    public GravityEffect(double gravityLevel, double maxFallSpeed) {
        this.gravityLevel = gravityLevel;
        this.maxFallSpeed = maxFallSpeed;
    }

    public double getGravityLevel() {
        return gravityLevel;
    }

    public double getMaxFallSpeed() {
        return maxFallSpeed;
    }
}
