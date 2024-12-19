package org.example.todofe;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;

import javafx.scene.input.MouseEvent;

import java.util.List;

public class ActivityController {

    // Skapar objekt av service
    ActivityService activityService = new ActivityService();

    // Variabel för markerad aktivitet
    private Activity selectedActivity = null;

    // Java FX element
    @FXML
    private TableView<Activity> result_tableView;

    @FXML
    private TableColumn<Activity, Integer> tableColumn_id;

    @FXML
    private TableColumn<Activity, String> tableColumn_name;

    @FXML
    private TableColumn<Activity, String> tableColumn_description;

    @FXML
    private TableColumn<Activity, Boolean> tableColumn_done;

    @FXML
    private TextArea textField_status;

    @FXML
    private TextField textfield_description;

    @FXML
    private TextField textfield_title;

    // Metod som initialiserar celler i tabellen
    @FXML
    void initialize() {
        tableColumn_id.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        tableColumn_name.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTitle()));
        tableColumn_description.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDescription()));
        tableColumn_done.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isDone()));
    }

    // Metod som körs när man klickar på en rad
    @FXML
    void tableView_selectedRow(MouseEvent event) {
        // Sätter vald aktivitet från den markerade raden
        selectedActivity = result_tableView.getSelectionModel().getSelectedItem();
        if (selectedActivity != null) {

            // Sätter title och description till fälten från vald aktivitet
            textfield_description.setText(selectedActivity.getDescription());
            textfield_title.setText(selectedActivity.getTitle());

            // Uppdaterar statusfält med vilken aktivitet som är vald
            textField_status.setText(("Selected activity: ") + selectedActivity.toString());
        }
    }

    // Metod för att lägga till aktivitet
    @FXML
    void button_add(ActionEvent event) {
        String activityName = textfield_title.getText();
        String activityDescription = textfield_description.getText();

        // Kontrollerar att fälten inte är tomma
        if (activityName.isEmpty() || activityDescription.isEmpty()) {
            textField_status.setText("Title and description must not be empty");
            return;
        }
        try {
            // Lägger till aktivitet
            Activity createdActivity = activityService.addActivity(activityName, activityDescription);
            textField_status.setText("Activity created successfully" + createdActivity.toString());

        } catch (Exception e) {
            textField_status.setText("Error: " + e.getMessage());
        }

        updateTableView();
    }

    // Metod för att ta bort aktivitet
    @FXML
    void button_delete(ActionEvent event) {
        if (selectedActivity != null) {
            try {
                // Tar bort aktivitet med ID från markerad aktivitet
                activityService.deleteActivity(selectedActivity.getId());
                textField_status.setText("Deleted Activity successfully" + selectedActivity);
            } catch (Exception e) {
                textField_status.setText("Error: " + e.getMessage());
            }
        }

        updateTableView();
    }

    // Metod för att redigera aktivitet
    @FXML
    void button_edit(ActionEvent event) {
        if (selectedActivity != null) {
            String activityName = textfield_title.getText();
            String activityDescription = textfield_description.getText();

            // Kollar så fälten inte är tomma
            if (activityName.isEmpty() || activityDescription.isEmpty()) {
                textField_status.setText("Title and description must not be empty");
                return;
            }

            try {
                // Uppdaterar aktiviteten som är markerad med nya värden för namn och description
                Activity updatedActivity = activityService.editActivity(
                        selectedActivity.getId(),
                        activityName,
                        activityDescription);

                textField_status.setText("Updated activity: " + updatedActivity);
            } catch (Exception e) {
                textField_status.setText("Error: " + e.getMessage());
            }
        }

        updateTableView();
    }

    // Metod för att markera aktivitet som done
    @FXML
    void button_markAsDone(ActionEvent event) {
        if (selectedActivity != null) {
            try {
                // Uppdaterar vald aktivitet till done
                Activity updatedActivity = activityService.markAsDone(
                        selectedActivity.getId(),
                        selectedActivity.getTitle(),
                        selectedActivity.getDescription());

                textField_status.setText("Marked as done: " + updatedActivity);
            } catch (Exception e) {
                textField_status.setText("Error: " + e.getMessage());
            }
        }

        updateTableView();
    }


    /*   Visar uppdaterad data i tabellen.
    Kunde skippat denna i efterhand då jag senare valde att uppdatera med de andra metoderna också.
    På grund av att denna metod skriver ut "List updated..." vilket jag inte ville ha till de andra knapparna
    så kunde jag inte lösa det utan att ha flera metoder där jag duplicerar kod..
    Hade jag haft mer tid så hade jag nog tagit bort hela knappen för show all och klarat mig med
    en metod för att uppdatera tableview som utförs automatiskt vid körning av de andra metoderna.
     */
    @FXML
    void button_showAll(ActionEvent event) {
        try {
            List<Activity> activities = activityService.getActivities();
            result_tableView.setItems(FXCollections.observableArrayList(activities));
            textField_status.setText("List updated successfully");
        } catch (Exception e) {
            textField_status.setText("Error" + e.getMessage());
        }

        /*
        Kunde inte bara köra updatedTableView här pga nestlad try catch som då inte visar något fel
        i den ena try catchen då den verkar tycka att det räcker att det hanterats i en av dem???
        Kan visa under presentation.
        */
        refreshUI();
    }


    // Metod som ppdaterar tabellen utan att skriva ut list updated successfully.
    private void updateTableView() {
        try {
            List<Activity> activities = activityService.getActivities();
            result_tableView.setItems(FXCollections.observableArrayList(activities));
        } catch (Exception e) {
            textField_status.setText("Error" + e.getMessage());
        }
        refreshUI();
    }

    // Metod som rensar fält och avmarkerar markerad rad.
    // Kunde legat i updateTableView om det inte var för problemet som jag beskrev ovan.
    private void refreshUI() {
        result_tableView.refresh();
        textfield_title.clear();
        textfield_description.clear();
        selectedActivity = null;
    }


}