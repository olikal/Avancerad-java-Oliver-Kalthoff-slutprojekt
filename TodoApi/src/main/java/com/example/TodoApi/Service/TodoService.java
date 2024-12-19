package com.example.TodoApi.Service;

import com.example.TodoApi.Model.Activity;

import java.util.ArrayList;
import java.util.List;

// Service klass som implementerar mitt interface och sparar data i arraylist
public class TodoService implements TodoInterface{

    // Skapar min datastructure lista, arraylist
    private List<Activity> activityList = new ArrayList<>();
    // Counter för att generera ID för varje tillagd aktivitet
    private int idCounter = 1;

    // Konstruktor där jag lägger till några testaktiviteter
    public TodoService(){
        addActivity(new Activity("Läs en bok", "Läs kapitel 1 och 2", false));
        addActivity(new Activity("Träna", "Spring 2km", false));
        addActivity(new Activity("Sov", "Ta en nap", false));
    }

    // Metod för att hämta alla aktiviteter
    @Override
    public List<Activity> getAllActivities() {
        return activityList;
    }

    // Hämtar specifik aktivitet via ID
    @Override
    public Activity getActivityById(int id) {
        // Stream för att hitta ID
        return activityList.stream()
                // Filter som matchar Id
                .filter(activity -> activity.getId() == id)
                // Tar första matchande aktivitet
                .findFirst()
                // Returnerar null om inget hittas på angivet Id
                .orElse(null);

    }

    // Hämtar aktivitet baserat på titel
    @Override
    public Activity getActivityByTitle(String title) {
        return activityList.stream()
                .filter(activity -> activity.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }
    // Lägger till ny aktivitet i listan
    @Override
    public Activity addActivity(Activity activity) {
        // Kollar så aktiviteten inte är null
        if(activity != null) {
            // Sätter unikt ID och ökar räknare med 1
            activity.setId(idCounter++);
            // Lägger till aktiviteten i listan
            activityList.add(activity);
        }
        // Returnerar aktiviteten
        return activity;
    }

    // Uppdaterar aktivitet baserat på Id
    @Override
    public Activity updateActivity(int id, Activity updatedActivity) {
        // Loopar lista för att hitta Id
        for (Activity activity : activityList) {
            if(activity.getId() == id) {
                // När Id hittas uppdateras egenskaperna med ny information
                activity.setTitle(updatedActivity.getTitle());
                activity.setDescription(updatedActivity.getDescription());
                activity.setDone(updatedActivity.isDone());
                return activity;

            }
        }
        return null;
    }

    // Tar bort aktivitet baserat på Id
    @Override
    public boolean deleteActivity(int id) {
        // RemoveIf för att ta bort aktivitet vid matchning av Id
        return activityList.removeIf(activity -> activity.getId() == id);

    }
}
