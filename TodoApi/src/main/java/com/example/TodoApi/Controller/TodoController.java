package com.example.TodoApi.Controller;

import com.example.TodoApi.Model.Activity;
import com.example.TodoApi.Service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
// BasURL för alla metoder i Controller
@RequestMapping("api/activities")
public class TodoController {
    // Skapar todoservice där logik och aktiviteter hanteras
    private final TodoService todoService = new TodoService();

    // GET-metod för att hämta alla aktiviteter
    @GetMapping
    public List<Activity> getAllActivities() {
        return todoService.getAllActivities();
    }

    // GET-metod för att hämta specicik aktivitet med Id
    @GetMapping("/{id}")
    public ResponseEntity<Activity> getActivityById(@PathVariable int id) {
        Activity activity = todoService.getActivityById(id);
        if(activity != null) {
            // Om aktivitet hittas returnera med status 200
            return ResponseEntity.ok(activity);
        } else {
            // Annars returnera status 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // GET-metod för att hämta specifik aktivitet med titel
    @GetMapping("/title/{title}")
    public ResponseEntity<Activity> getActivityByTitle(@PathVariable String title) {
        Activity activity = todoService.getActivityByTitle(title);
        if(activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // POST-metod för att lägga till aktivitet
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody @Validated Activity activity) {
        // Skickar in ny aktivitet till serviceklassens addActivity
        Activity createdActivity = todoService.addActivity(activity);
        if(activity != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdActivity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // PUT-metod för att redigera aktivitet baserat på Id
    @PutMapping("/{id}")
    public ResponseEntity<Activity> updateActivity(@PathVariable int id, @RequestBody @Validated Activity updatedActivity) {
        // Uppdaterar aktivitet med hjälp av id och skriver den nya informationen
        Activity activity = todoService.updateActivity(id, updatedActivity);
        if(activity != null) {
            return ResponseEntity.ok(activity);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // DELETE-metod för att ta bort aktivitet
    @DeleteMapping("/{id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable int id) {
        // Tar bort aktivitet med angivet Id
        boolean deleted = todoService.deleteActivity(id);
        if(deleted) {
            // Om det gick bra skickas OK, 200.
            // Har förstått att man egentligen ska använda NO_CONTENT men jag vill visa bekräftelse
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            // Annars skickas not found, 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
