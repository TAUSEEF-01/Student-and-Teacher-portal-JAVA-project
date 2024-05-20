package com.example.demo;

import com.example.demo.client.Client;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;
import java.time.*;
import java.time.format.DateTimeFormatter;


public class messageController {
    public AnchorPane pane;
    public ScrollPane scrollPain;
    public VBox vBox;
//    public TextArea txtMsg;

    public TextField txtMsg;
//    public Text txtLabel;


    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private String clientName = "Client";

    @FXML
    private Button cancelButton;

    @FXML
    private VBox vBox2;

    private Client client;


    public void initialize() {

        try {
            socket = new Socket("localhost", 3333);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        if (client == null) {
            System.out.println("new client!");
            client = new Client(socket, StudentLogin.student.fname);
        } else {
            System.out.println("Client is already connected");
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()) {
                    String receivingMsg;
                    receivingMsg = client.receiveMessage();

                    synchronized (this) {
                        if (!receivingMsg.isEmpty()) {
                            System.out.println("Message received: " + receivingMsg + " this was the message");
                            Platform.runLater(() -> {
                                try {
                                    sendMsg(receivingMsg, 2);
                                } catch (Exception e) {
                                    System.out.println("The error: ");
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                }
            }
        }).start();


        this.vBox.heightProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                scrollPain.setVvalue((Double) newValue);
            }
        });
    }


    public void txtMsgOnAction(ActionEvent actionEvent) {
        sendButtonOnAction(actionEvent);
    }

    public void sendButtonOnAction(ActionEvent actionEvent) {

        String message = txtMsg.getText();
        txtMsg.clear();
        sendMsg(message, 1);
        client.sendMessage(message);

        System.out.println("done!!");
    }


    public void sendMsg(String msgToSend, int option) {
        if (!msgToSend.isEmpty() && msgToSend != "") {
            if (!msgToSend.matches(".*\\.(png|jpe?g|gif)$")) {

                HBox hBox = new HBox();
                if (option == 1) {
                    hBox.setAlignment(Pos.CENTER_RIGHT);
                    hBox.setPadding(new Insets(5, 5, 0, 10));

                    Text text = new Text(msgToSend);
                    text.setStyle("-fx-font-size: 14");
                    TextFlow textFlow = new TextFlow(text);

//              #0693e3 #37d67a #40bf75
                    textFlow.setStyle("-fx-background-color: #0693e3; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(1, 1, 1));

                    hBox.getChildren().add(textFlow);

                    HBox hBoxTime = new HBox();

                    hBoxTime.setAlignment(Pos.CENTER_RIGHT);
                    hBoxTime.setPadding(new Insets(0, 5, 5, 10));

                    String stringTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                    Text time = new Text(stringTime);
                    time.setStyle("-fx-font-size: 8");

                    hBoxTime.getChildren().add(time);

                    vBox.getChildren().add(hBox);
                    vBox.getChildren().add(hBoxTime);
                } else {
                    hBox.setAlignment(Pos.CENTER_LEFT);
                    hBox.setPadding(new Insets(5, 5, 0, 10));


                    Text text = new Text(msgToSend);
                    text.setStyle("-fx-font-size: 14");
                    TextFlow textFlow = new TextFlow(text);

//              #0693e3 #37d67a #40bf75 #51BDC1
                    textFlow.setStyle("-fx-background-color: #51BDC1; -fx-font-weight: bold; -fx-color: white; -fx-background-radius: 20px");
                    textFlow.setPadding(new Insets(5, 10, 5, 10));
                    text.setFill(Color.color(1, 1, 1));

                    hBox.getChildren().add(textFlow);

                    HBox hBoxTime = new HBox();

                    hBoxTime.setAlignment(Pos.CENTER_LEFT);
                    hBoxTime.setPadding(new Insets(15, 5, 5, 10));

                    String stringTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm"));
                    Text time = new Text(stringTime);
                    time.setStyle("-fx-font-size: 8");

                    hBoxTime.getChildren().add(time);

                    vBox.getChildren().add(hBox);
                    vBox.getChildren().add(hBoxTime);
                }

                txtMsg.clear();
            }
        }
    }

    public static void receiveMessage(String msg, VBox vBox) throws IOException {
//    public static void receiveMessage(String msg) throws IOException {
        if (msg.matches(".*\\.(png|jpe?g|gif)$")) {
            HBox hBoxName = new HBox();
            hBoxName.setAlignment(Pos.CENTER_LEFT);
            Text textName = new Text(msg.split("[-]")[0]);
            TextFlow textFlowName = new TextFlow(textName);
            hBoxName.getChildren().add(textFlowName);

            Image image = new Image(msg.split("[-]")[1]);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(200);
            imageView.setFitWidth(200);
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5, 5, 5, 10));
            hBox.getChildren().add(imageView);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    vBox.getChildren().add(hBoxName);
                    vBox.getChildren().add(hBox);
                }
            });

        } else {
            String name = msg.split("-")[0];
            String msgFromServer = msg.split("-")[1];

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER_LEFT);
            hBox.setPadding(new Insets(5, 5, 5, 10));

            HBox hBoxName = new HBox();
            hBoxName.setAlignment(Pos.CENTER_LEFT);
            Text textName = new Text(name);
            TextFlow textFlowName = new TextFlow(textName);
            hBoxName.getChildren().add(textFlowName);

            Text text = new Text(msgFromServer);
            TextFlow textFlow = new TextFlow(text);
            textFlow.setStyle("-fx-background-color: #abb8c3; -fx-font-weight: bold; -fx-background-radius: 20px");
            textFlow.setPadding(new Insets(5, 10, 5, 10));
            text.setFill(Color.color(0, 0, 0));

            hBox.getChildren().add(textFlow);

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    vBox.getChildren().add(hBoxName);
                    vBox.getChildren().add(hBox);
                }
            });
        }
    }

    public void redirectMainPage() {
        try {
            Parent root2 = FXMLLoader.load(getClass().getResource("student_mainPage.fxml"));
            Stage loginStage = new Stage();
//            loginStage.initStyle(StageStyle.UNDECORATED);
            loginStage.setScene(new Scene(root2, 900, 580));
            loginStage.setResizable(false);
            loginStage.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }
    }

    public void cancelButtonOnAction(ActionEvent actionEvent) {
        redirectMainPage();

        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }
}
