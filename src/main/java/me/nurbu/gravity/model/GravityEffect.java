package me.nurbu.gravity.model;

public class GravityEffect {
    private double gravityLevel;
    private double maxFallSpeed;

    public GravityEffect() {
    }

    public void setGravityLevel(double gravityLevel) {
        this.gravityLevel = gravityLevel;
    }

    public void setMaxFallSpeed(double maxFallSpeed) {
        this.maxFallSpeed = maxFallSpeed;
    }

    public double getGravityLevel() {
        return gravityLevel;
    }

    public double getMaxFallSpeed() {
        return maxFallSpeed;
    }
}
