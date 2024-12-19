package com.example.TodoApi.Model;


import jakarta.validation.constraints.NotBlank;

// Klass för activity model
public class Activity {
    private int id;
    // Annotation validering för att se till att titel inte är tom
    @NotBlank
    private String title;
    // Annotation validering för att se till att description inte är tom
    @NotBlank
    private String description;
    private boolean done;


    // Konstruktorer
    public Activity() {}

    public Activity(String title, String description, boolean done) {
        this.title = title;
        this.description = description;
        this.done = done;
    }

    // Getters och setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
    
}
