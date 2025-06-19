package com.mongol.animewar.model;

import jakarta.persistence.*;

@Entity
public class Characters {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String world;

    // Constructors
    public Characters() {}

    public Characters(String name, String world) {
        this.name = name;
        this.world = world;
    }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getWorld() { return world; }
    public void setWorld(String world) { this.world = world; }
}
