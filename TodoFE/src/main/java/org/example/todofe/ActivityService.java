package org.example.todofe;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ActivityService {

    // ObjectMapper för omvandling mellan JSON och java object
    ObjectMapper mapper = new ObjectMapper();


    // Metod för add activity. Skickar POST till backend
    public Activity addActivity(String Name, String Description) throws IOException {
            // Skapar activity med valt namn, description och med done = false
            Activity activity = new Activity(Name, Description, false);

            // Mappar objektet till JSON sträng
            String json = mapper.writeValueAsString(activity);

            // Skapar URL och anslutning till backend
            URL url = new URL("http://localhost:8080/api/activities"); // URL till API dit POST request skickas
            HttpURLConnection connection = (HttpURLConnection) url.openConnection(); // Connection för skicka/ta emot requests
            connection.setRequestMethod("POST"); // Metod som ska användas
            connection.setRequestProperty("Content-Type", "application/json"); // Format som ska användas, JSON
            connection.setDoOutput(true); // Möjliggör data i body

            // Skriver JSON till output stream som byte-array
            try (OutputStream os = connection.getOutputStream()) {
                // Konverterar json till byte-array för att skicka via http
                byte[] input = json.getBytes(StandardCharsets.UTF_8);
                // Skriver byte-array till output stream som skcikas med PUT-request till server
                os.write(input);
            }

            // Läser svar från server
            String response = readResponse(connection);

            // Gör om svaret från JSON till activity object och returnerar
            return mapper.readValue(response, Activity.class);
    }

    // Metod för att ta bort aktivitet, skickar DELETE request till backend
    public void deleteActivity(Integer Id) throws IOException {
        URL url = new URL("http://localhost:8080/api/activities/" + Id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        String response = readResponse(connection);

    }

    // Metod för att redigera aktivitet, skickar PUT request till backend
    public Activity editActivity(Integer Id, String Name, String Description) throws IOException {

        // Skapar aktivitet med uppdaterade fält. Sätter false pga nya todo aktiviteter borde inte vara utförda
        Activity activity = new Activity(Name, Description, false);

        String json = mapper.writeValueAsString(activity);

        URL url = new URL("http://localhost:8080/api/activities/" + Id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        String response = readResponse(connection);

        return mapper.readValue(response, Activity.class);


    }

    // Metod för att markera aktivitet som done, PUT request till backend med done = true
    public Activity markAsDone(Integer Id, String Name, String Description) throws IOException {
        Activity activity = new Activity(Name, Description, true);

        String json = mapper.writeValueAsString(activity);

        URL url = new URL("http://localhost:8080/api/activities/" + Id);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = json.getBytes(StandardCharsets.UTF_8);
            os.write(input);
        }

        String response = readResponse(connection);

        return mapper.readValue(response, Activity.class);

    }

    // Metod för att hämta alla aktiviteter
    public List<Activity> getActivities() throws IOException {

        URL url = new URL("http://localhost:8080/api/activities");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        String response = readResponse(connection);

        // Gör om JSON lista till lista med activity object
        return mapper.readValue(response, new TypeReference<>() {
        });


    }

    // Hjälpmetod för att läsa svar från server
    private String readResponse(HttpURLConnection connection) throws IOException {
        // Skapar reader för att läsa från server
        BufferedReader reader;

        // Om vi får statuskod 200-299 läser vi data från server
        if (connection.getResponseCode() >= 200 && connection.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            // Annars visar vi felkod med felmeddelande
            throw new IOException(connection.getResponseCode() + " Something went wrong");
        }

        // Skapar stringbuilder för att bygga svaret som string
        StringBuilder response = new StringBuilder();
        // Läser varje rad från serversvar
        String line;
        while ((line = reader.readLine()) != null) {
            // Sparar varje rad i response
            response.append(line);
        }
        // stänger reader
        reader.close();
        // Returnerar svaret som string
        return response.toString();
    }


}