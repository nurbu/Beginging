package me.nurbu.gravity.model;

public class TimeOfDay {
    private final GravityEffect Morning;
    private final GravityEffect Afternoon;
    private final GravityEffect Nighttime;

    public TimeOfDay(GravityEffect Morning, GravityEffect Afternoon, GravityEffect Nighttime) {
        this.Morning = Morning;
        this.Afternoon = Afternoon;
        this.Nighttime = Nighttime;
    }

    public GravityEffect getMorning() {
        return Morning;
    }

    public GravityEffect getAfternoon() {
        return Afternoon;
    }

    public GravityEffect getNighttime() {
        return Nighttime;
    }
}

