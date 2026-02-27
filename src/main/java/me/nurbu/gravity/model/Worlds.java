package me.nurbu.gravity.model;

import lombok.Data;

import java.util.List;

@Data
public class Worlds {
    private String world;
    private List<Regions> regions;
}