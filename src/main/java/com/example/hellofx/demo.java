package com.example.hellofx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class demo {

    @FXML
    private VBox vbox;

    public void handleButtonClick(ActionEvent event) {
    }

    public void homeOnAction(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = fxmlLoader.load();

        // Get the scene from the current event source
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Set the new root in the current scene
        currentScene.setRoot(root);
    }


    private static class ClassSchedule {
        private String day;
        private String time;
        private String description;

        public ClassSchedule(String day, String time, String description) {
            this.day = day;
            this.time = time;
            this.description = description;
        }

        public String getDay() {
            return day;
        }

        public String getTime() {
            return time;
        }

        public String getDescription() {
            return description;
        }

        @Override
        public String toString() {
            return time + ": " + description;
        }
    }

    private ObservableList<ClassSchedule> todayClasses;
    private ObservableList<String> toDoItems;


    //@Override
    public void initialize() {
       // primaryStage.setTitle("Class Schedule");

        // Initialize the schedule
        List<ClassSchedule> schedule = initializeSchedule();

        // Get today's day of the week and date
        LocalDate today = LocalDate.now();
        // String dayOfWeek = today.getDayOfWeek().toString();
        String dayOfWeek ="Tuesday";
        String formattedDate = today.format(DateTimeFormatter.ofPattern("MMMM dd, yyyy"));

        // Filter today's classes
        todayClasses = FXCollections.observableArrayList();
        for (ClassSchedule cs : schedule) {
            if (cs.getDay().equalsIgnoreCase(dayOfWeek)) {
                todayClasses.add(cs);
            }
        }

        // UI Components

        Label dateLabel = new Label("Today is: " + dayOfWeek + ", " + formattedDate);
        ListView<ClassSchedule> listView = new ListView<>(todayClasses);
        listView.setCellFactory(param -> new ListCell<ClassSchedule>() {
            private int index = -1;

            @Override
            protected void updateItem(ClassSchedule item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null);
                } else {
                    setText(item.getTime() + ": " + item.getDescription());
                    setTextFill(Color.BLACK); // Set text color
                    setFont(Font.font("Arial", FontWeight.BOLD, 18)); // Set font
                    setStyle(index % 2 == 0 ? "-fx-background-color: skyblue; -fx-padding: 5px;" : "-fx-background-color: lavender; -fx-padding: 5px;"); // Set background color and padding
                    index++;
                }

            }
        });

        // To-Do List Components
        TextField toDoInput = new TextField();
        Button addButton = new Button("Add");
        toDoItems = FXCollections.observableArrayList();

        // Event handler for the Add button
        addButton.setOnAction(event -> {
            String newToDo = toDoInput.getText().trim();
            if (!newToDo.isEmpty()) {
                toDoItems.add(newToDo);
                toDoInput.clear();
            }
        });

        ListView<String> toDoListView = new ListView<>(toDoItems);

        // Layout
        //VBox layout = new VBox(10);
       // layout.setPadding(new Insets(10));
        vbox.getChildren().addAll(dateLabel, listView, toDoInput, addButton, toDoListView);

        // Set the scene
       // Scene scene = new Scene(layout, 900, 580);
       // primaryStage.setScene(scene);
        //primaryStage.show();
    }


    private List<ClassSchedule> initializeSchedule() {
        List<ClassSchedule> schedule = new ArrayList<>();

        // Add more classes as needed
        schedule.add(new ClassSchedule("Sunday", "11:10 AM", "Math-2105  MIB"));
        schedule.add(new ClassSchedule("Monday", "9:50 AM", "EEE Lab-2113"));
        schedule.add(new ClassSchedule("Tuesday", "9:50 AM", "EEE-2103 SA"));
        schedule.add(new ClassSchedule("Tuesday", "11:10 AM", "OOP-2102 RAR"));
        schedule.add(new ClassSchedule("Tuesday", "2:00 AM", "EEE-2113 RAR"));
        schedule.add(new ClassSchedule("Wednesday", "9:50 AM", "CSE-2101 MTA"));
        schedule.add(new ClassSchedule("Wednesday", "11:10 AM", "Math-2105 MIB"));
        schedule.add(new ClassSchedule("Wednesday", "2:00 AM", "GED-2104 SP"));

        return schedule;
    }


}
