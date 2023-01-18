package com.sl.jmsprovider.core;

import com.sl.jmsprovider.jms.SLTextMessage;

import javax.jms.JMSException;
import javax.jms.Message;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;


public class Consumer implements SessionHandler {
    private BlockingQueue<Message> queue;
    private Socket socket;
    private ObjectOutputStream out;
    private Session session;

    public Consumer(BlockingQueue<Message> queue, Session session) {
        this.queue = queue;
        this.session = session;
        this.socket = session.getSocket();
        this.out = session.getOutputStream();
    }

    @Override
    public void close() {
        this.session.close();
    }


    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                SLTextMessage slTextMessage = (SLTextMessage) queue.take();
                out.writeObject(slTextMessage);
                out.flush();
            } catch (IOException e) {
                close();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

