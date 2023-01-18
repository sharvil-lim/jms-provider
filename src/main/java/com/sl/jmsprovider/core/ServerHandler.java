package com.sl.jmsprovider.core;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;

public class ServerHandler {
    private ServerSocket serverSocket;
    private LinkedList<Socket> sockets;

    public ServerHandler(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        this.sockets = new LinkedList<>();
    }

    public void start() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                sockets.add(socket);
                Session session = new Session(socket);
                Thread thread = new Thread(session);
                thread.start();
            }
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (sockets != null) {
                for (Socket socket : sockets) {
                    socket.close();
                }
            } if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
