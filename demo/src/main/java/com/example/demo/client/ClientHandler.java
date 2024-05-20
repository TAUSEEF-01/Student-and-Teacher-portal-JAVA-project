package com.example.demo.client;


import com.example.demo.StudentLogin;

import java.io.*;
import java.net.*;
import java.util.*;

public class ClientHandler implements Runnable {

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    public Socket socket;
    private BufferedReader buffReader;
    private BufferedWriter buffWriter;
    private String name;

    public ClientHandler(Socket socket, int id) {
        try {
            this.socket = socket;
            this.buffWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.buffReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            name = "ab" + id;

            this.name = StudentLogin.student.fname;

            System.out.println("Accessed first name from RegisterController: " + name);


            clientHandlers.add(this);
            boradcastMessage("SERVER " + name + " has entered in the room");

        } catch (IOException e) {
            closeAll(socket, buffReader, buffWriter);
        }
    }


    @Override
    public void run() {
        String messageFromClient;
        while (socket.isConnected()) {
            try {
                synchronized (this) {
                    messageFromClient = buffReader.readLine();
                    System.out.println(messageFromClient);
                    boradcastMessage(messageFromClient);
                }
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
                break;
            }
        }
    }

    public void boradcastMessage(String messageToSend) {
        messageToSend = messageToSend;
        System.out.println(messageToSend);

        for (ClientHandler clientHandler : clientHandlers) {
            try {
                if (clientHandler.socket.getPort() != socket.getPort()) {
                    clientHandler.buffWriter.write(messageToSend);
                    clientHandler.buffWriter.newLine();
                    clientHandler.buffWriter.flush();
                }
            } catch (IOException e) {
                closeAll(socket, buffReader, buffWriter);
            }
        }

    }


    // notify if the user left the chat
    public void removeClientHandler() {
        clientHandlers.remove(this);
        boradcastMessage("server " + name + " has gone");
    }

    public void closeAll(Socket socket, BufferedReader buffReader, BufferedWriter buffWriter) {

        // handle the removeClient funciton
        removeClientHandler();
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
