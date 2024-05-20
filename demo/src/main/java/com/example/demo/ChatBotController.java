package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class ChatBotController {

    @FXML
    private TextArea area;
    @FXML
    private TextField field;
    @FXML
    private Button send;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button returnButton;

    private LocalTime time = LocalTime.now();
    private LocalDate date = LocalDate.now();
    private Random random = new Random();

    @FXML
    private void returnButtonOnAction() {
        Stage stage = (Stage) returnButton.getScene().getWindow();
        stage.close();

        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("student_mainPage.fxml"));
            Stage registerStage = new Stage();
//            registerStage.initStyle(StageStyle.UNDECORATED);
            registerStage.setScene(new Scene(root2, 900, 580));
            registerStage.setResizable(false);
            registerStage.show();

        } catch(Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    @FXML
    private void handleUserInput() {
        String message = field.getText().toLowerCase();
        area.appendText("You: " + field.getText() + "\n");
        field.clear();

        if (message.contains("how are you")) {
            int num = random.nextInt(3);
            if (num == 0) {
                bot("I'm fine! What about you?");
            } else if (num == 1) {
                bot("I am good, thanks for asking!");
            } else {
                bot("I am great, thanks for asking!");
            }
        } else if (message.contains("you") && (message.contains("smart") || message.contains("good"))) {
            bot("Thank you!");
        } else if (message.contains("welcome")) {
            bot("You are so polite. How can I help you?");
        } else if (message.contains("hi") || message.contains("hello") || message.contains("hey")) {
            int num = random.nextInt(4);
            if (num == 0) {
                bot("Hi");
            } else if (num == 1) {
                bot("Hello");
            } else if (num == 2) {
                bot("Hey");
            } else {
                bot("Hello buddy");
            }
        } else if (message.contains("by")) {
            bot("Bye, see you soon!");
        } else if (message.contains("i am good") || message.contains("i am great") || (message.contains("i am") && message.contains("fine"))) {
            bot("Good to hear.");
        } else if (message.contains("thank")) {
            int num = random.nextInt(4);
            if (num == 0) {
                bot("You're welcome.");
            } else if (num == 1) {
                bot("My pleasure.");
            } else if (num == 2) {
                bot("Happy to help.");
            } else {
                bot("That's why I'm here.");
            }
        } else if (message.contains("what") && message.contains("name")) {
            if (message.contains("your")) {
                bot("I'm Bot.");
            }
            if (message.contains("my")) {
                bot("You are a genius person!");
            }
        } else if (message.contains("change")) {
            bot("Sorry, I can't change anything.");
        } else if (message.contains("time")) {
            String ctime;
            if (time.getHour() > 12) {
                int hour = time.getHour() - 12;
                ctime = hour + ":" + time.getMinute() + ":" + time.getSecond() + " PM";
            } else {
                ctime = time.getHour() + ":" + time.getMinute() + ":" + time.getSecond() + " AM";
            }
            bot(ctime);
        } else if (message.contains("date") || message.contains("month") || message.contains("year") || message.contains("day")) {
            String cdate = date.getDayOfWeek() + ", " + date.getDayOfMonth() + " " + date.getMonth() + " " + date.getYear();
            bot(cdate);
        } else if (message.contains("good morning")) {
            bot("Good morning! Have a nice day!");
        } else if (message.contains("good night")) {
            bot("Good night! Have sweet dreams!");
        } else if (message.contains("good evening")) {
            bot("Good evening!");
        } else if (message.contains("good") && message.contains("noon")) {
            bot("Good afternoon!");
        } else if (message.contains("clear") && (message.contains("screen") || message.contains("chat"))) {
            bot("Wait a few seconds...");
            area.clear();
        } else {
            try {
                URL url = new URL("https://google.co.in");
                URLConnection connection = url.openConnection();
                connection.connect();
                bot("Here are some results for you...");
                java.awt.Desktop.getDesktop().browse(java.net.URI.create("http://www.google.com/search?hl=en&q=" + message.replace(" ", "+") + "&btnG=Google+Search"));
            } catch (Exception e) {
                int num = random.nextInt(3);
                if (num == 0) {
                    bot("Sorry, I can't understand you!");
                } else if (num == 1) {
                    bot("Sorry, I don't understand.");
                } else {
                    bot("My apologies... I don't understand.");
                }
            }
        }
    }

    private void bot(String message) {
        area.appendText("Bot: " + message + "\n");
    }
}
