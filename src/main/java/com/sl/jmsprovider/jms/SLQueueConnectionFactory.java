package com.sl.jmsprovider.jms;

import javax.jms.Connection;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.QueueConnection;
import java.io.IOException;
import java.net.Socket;

public class SLQueueConnectionFactory implements javax.jms.QueueConnectionFactory {
    private Socket socket;

    public SLQueueConnectionFactory(int port) {
        try {
            this.socket = new Socket("localhost", port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public QueueConnection createQueueConnection() throws JMSException {
        SLQueueConnection SLQueueConnection = new SLQueueConnection();
        SLQueueConnection.setSocket(socket);
        return SLQueueConnection;
    }

    @Override
    public QueueConnection createQueueConnection(String userName, String password) throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection() throws JMSException {
        return null;
    }

    @Override
    public Connection createConnection(String userName, String password) throws JMSException {
        return null;
    }

    @Override
    public JMSContext createContext() {
        return null;
    }

    @Override
    public JMSContext createContext(String userName, String password) {
        return null;
    }

    @Override
    public JMSContext createContext(String userName, String password, int sessionMode) {
        return null;
    }

    @Override
    public JMSContext createContext(int sessionMode) {
        return null;
    }
}
