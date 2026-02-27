package me.nurbu.gravity.model;

import lombok.Data;

@Data
public class TimeOfDay {
    private GravityEffect Morning;
    private GravityEffect Afternoon;
    private GravityEffect Night;
}
