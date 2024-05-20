package com.example.demo.server;

import com.example.demo.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    // create serverSocket class
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public void serverStart() {
        try {
            int id = 1;
            System.out.println("Server started");
            // check and loop the serverSocket
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                System.out.println("New Friend Connected");

                ClientHandler clientHandler = new ClientHandler(socket, id++);
                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // this will close the com.example.demo.server
    public void closerServer() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(3333);
        Server server = new Server(serverSocket);
        server.serverStart();
    }
}
