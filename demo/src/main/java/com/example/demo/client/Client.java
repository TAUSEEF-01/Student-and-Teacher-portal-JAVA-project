package com.example.demo.client;


import java.io.*;
import java.net.*;

public class Client {
    // private classes for the com.example.demo.client
    private Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;


    private String tempMessage = "";

    public Client(Socket socket, String name) {
        try {
            // Constructors of all the private classes
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.name = name;
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }

        readMessage();
    }

    public void sendMessage(String messageToSend) {
        try {
            System.out.println(this.name + ": " + messageToSend);
            tempMessage = messageToSend;

            buffWriter.write(messageToSend);
            buffWriter.newLine();
            buffWriter.flush();
        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }

    // method to read messages using thread
    public void readMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String msgFromGroupChat;
                while (socket.isConnected()) {
                    try {
                        tempMessage = "";
                        msgFromGroupChat = buffReader.readLine();
                        System.out.println("readMessage: " + msgFromGroupChat);
                        tempMessage = msgFromGroupChat;
                    } catch (IOException e) {
                        closeAll(socket, buffReader, buffWriter);
                    }
                }
            }
        }).start();
    }


    public String receiveMessage() {
        String temp = tempMessage;
        tempMessage = "";
        return temp;
    }

    // method to close everything in the socket
    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {
        try {
            if (buffReader != null) {
                buffReader.close();
            }
            if (buffWriter != null) {
                buffWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
    }

}

