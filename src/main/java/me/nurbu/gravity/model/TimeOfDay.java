package me.nurbu.gravity.model;

public class TimeOfDay {
    private GravityEffect Morning;
    private GravityEffect Afternoon;
    private GravityEffect Nighttime;

    public void setMorning(GravityEffect morning) {
        Morning = morning;
    }

    public void setAfternoon(GravityEffect afternoon) {
        Afternoon = afternoon;
    }

    public void setNighttime(GravityEffect nighttime) {
        Nighttime = nighttime;
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

