package com.sl.jmsprovider.jms;

import javax.jms.*;
import java.io.*;
import java.net.Socket;

public class SLQueueSession implements QueueSession {
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    public void setSocket(Socket socket) {
        this.socket = socket;
        setIn();
        setOut();
    }

    public void setIn() {
        try {
            this.in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setOut() {
        try {
            this.out = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return null;
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        return null;
    }

    @Override
    public Message createMessage() throws JMSException {
        return null;
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        return null;
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
        return null;
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return null;
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        return new SLTextMessage();
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return null;
    }

    @Override
    public boolean getTransacted() throws JMSException {
        return false;
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        return 0;
    }

    @Override
    public void commit() throws JMSException {

    }

    @Override
    public void rollback() throws JMSException {

    }

    @Override
    public void close() throws JMSException {

    }

    @Override
    public void recover() throws JMSException {

    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return null;
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {

    }

    @Override
    public void run() {

    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createConsumer(Destination destination, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic topic, String sharedSubscriptionName, String messageSelector) throws JMSException {
        return null;
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        return null;
    }

    @Override
    public Topic createTopic(String topicName) throws JMSException {
        return null;
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name) throws JMSException {
        return null;
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic topic, String name, String messageSelector, boolean noLocal) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name) throws JMSException {
        return null;
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic topic, String name, String messageSelector) throws JMSException {
        return null;
    }

    @Override
    public QueueReceiver createReceiver(Queue queue) throws JMSException {
        SLQQueueReceiver queueReceiver = new SLQQueueReceiver();
        SLHandshakeMessage slHandshakeMessage = new SLHandshakeMessage();

        try {
            slHandshakeMessage.setConsumer();
            slHandshakeMessage.setQueueName(queue.getQueueName());
            out.writeObject(slHandshakeMessage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        queueReceiver.setSocket(this.socket);
        queueReceiver.setInput(this.in);
        return queueReceiver;
    }

    @Override
    public QueueReceiver createReceiver(Queue queue, String messageSelector) throws JMSException {
        return null;
    }

    @Override
    public QueueSender createSender(Queue queue) throws JMSException {
        SLQueueSender queueSender = new SLQueueSender();
        SLHandshakeMessage slHandshakeMessage = new SLHandshakeMessage();

        try {
            slHandshakeMessage.setProducer();
            slHandshakeMessage.setQueueName(queue.getQueueName());
            out.writeObject(slHandshakeMessage);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        queueSender.setOutput(this.out);
        return queueSender;
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return null;
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String messageSelector) throws JMSException {
        return null;
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return null;
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return null;
    }

    @Override
    public void unsubscribe(String name) throws JMSException {

    }
}
