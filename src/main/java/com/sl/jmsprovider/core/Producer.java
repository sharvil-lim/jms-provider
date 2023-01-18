package com.sl.jmsprovider.core;

import com.sl.jmsprovider.jms.SLTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Producer implements SessionHandler {
    BlockingQueue<Message> queue;
    Socket socket;
    ObjectInputStream in;
    Session session;

    public Producer(BlockingQueue<Message> queue, Session session) {
        this.queue = queue;
        this.session = session;
        this.socket = session.getSocket();
        this.in = session.getInputStream();
    }

    @Override
    public void close() {
        this.session.close();
    }

    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                SLTextMessage message = (SLTextMessage) in.readObject();
                queue.put(message);
            } catch (IOException | ClassNotFoundException e) {
                close();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
