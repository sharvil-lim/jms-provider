package com.sl.jmsprovider.jms;

import javax.jms.*;
import java.io.*;
import java.net.Socket;

public class SLQQueueReceiver implements QueueReceiver {
    private Socket socket;
    private ObjectInputStream in;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setInput(ObjectInputStream in) {
        this.in = in;
    }

    @Override
    public Queue getQueue() throws JMSException {
        return null;
    }

    @Override
    public String getMessageSelector() throws JMSException {
        return null;
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return null;
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        new Thread(() -> {
            try {
                while (socket.isConnected()) {
                    SLTextMessage message = (SLTextMessage) in.readObject();
                    listener.onMessage(message);
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();
    }

    @Override
    public Message receive() throws JMSException {
        return null;
    }

    @Override
    public Message receive(long timeout) throws JMSException {
        return null;
    }

    @Override
    public Message receiveNoWait() throws JMSException {
        return null;
    }

    @Override
    public void close() throws JMSException {

    }
}
