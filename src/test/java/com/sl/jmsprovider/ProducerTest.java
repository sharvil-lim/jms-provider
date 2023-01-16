package com.sl.jmsprovider;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Properties;

public class ProducerTest {

    public static void main(String[] args) {
        try {
//            Properties p = new Properties();
//            p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sl.jmsprovider.jndi.SLJMSInitialContextFactory");
//            p.setProperty("connectionFactory.ConnectionFactory", "1234");
//            p.setProperty("queue.sampleQueue", "Sample");

            InitialContext initialContext = new InitialContext();

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) initialContext.lookup("sampleQueue");
            QueueSender queueSender = queueSession.createSender(queue);
            TextMessage textMessage = queueSession.createTextMessage();

            textMessage.setText("Hello World");
            queueSender.send(textMessage);

            textMessage.setText("Hello World again");
            queueSender.send(textMessage);

            textMessage.setText("ok");
            queueSender.send(textMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
