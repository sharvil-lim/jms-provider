package com.sl.jmsprovider.core;


import com.sl.jmsprovider.jms.SLHandshakeMessage;

import javax.jms.Message;
import java.io.*;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Session implements Runnable {
    private SessionHandler sessionHandler;
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public Session(Socket socket) {
        try {
            this.socket = socket;
            this.in = new ObjectInputStream(socket.getInputStream());
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            close();
        }
    }

    public void close() {
        try {
            if (socket != null) {
                socket.close();
            } if (in != null) {
                in.close();
            } if (out != null) {
                out.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public ObjectInputStream getInputStream() {
        return this.in;
    }

    public ObjectOutputStream getOutputStream() {
        return this.out;
    }

    @Override
    public void run() {
        try {
            SLHandshakeMessage handshakeMessage = (SLHandshakeMessage) in.readObject();
            QueueManager queueManager = QueueManager.instantiate();

            if (!(queueManager.hasQueue(handshakeMessage.getQueueName()))) {
                queueManager.makeQueue(handshakeMessage.getQueueName());
            }

            BlockingQueue<Message> queue = queueManager.getQueue(handshakeMessage.getQueueName());

            if (handshakeMessage.checkIfConsumer()) {
                this.sessionHandler = new Consumer(queue, this);
            } else if (handshakeMessage.checkIfProducer()) {
                this.sessionHandler = new Producer(queue, this);
            } else {
                close();
                return;
            }

            Thread thread = new Thread(sessionHandler);
            thread.start();
        } catch (IOException | ClassNotFoundException e) {
            close();
        }
    }
}