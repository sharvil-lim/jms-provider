package com.sl.jmsprovider;

import com.sl.jmsprovider.jms.SLMyListener;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

public class ConsumerTest {

    public static void main(String[] args) {
        try {

            InitialContext initialContext = new InitialContext();

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) initialContext.lookup("ConnectionFactory");
            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession queueSession = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            Queue queue = (Queue) initialContext.lookup("sampleQueue");
            QueueReceiver queueReceiver = queueSession.createReceiver(queue);

            MessageListener myListener = new SLMyListener();
            queueReceiver.setMessageListener(myListener);
        } catch (JMSException | NamingException e) {
            e.printStackTrace();
        }
    }
}
