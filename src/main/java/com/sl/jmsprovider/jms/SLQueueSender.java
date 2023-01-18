package com.sl.jmsprovider.jms;

import javax.jms.*;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SLQueueSender implements QueueSender {
    private ObjectOutputStream out;

    public void setOutput(ObjectOutputStream out) {
        this.out = out;
    }

    @Override
    public Queue getQueue() throws JMSException {
        return null;
    }

    @Override
    public void setDisableMessageID(boolean value) throws JMSException {

    }

    @Override
    public boolean getDisableMessageID() throws JMSException {
        return false;
    }

    @Override
    public void setDisableMessageTimestamp(boolean value) throws JMSException {

    }

    @Override
    public boolean getDisableMessageTimestamp() throws JMSException {
        return false;
    }

    @Override
    public void setDeliveryMode(int deliveryMode) throws JMSException {

    }

    @Override
    public int getDeliveryMode() throws JMSException {
        return 0;
    }

    @Override
    public void setPriority(int defaultPriority) throws JMSException {

    }

    @Override
    public int getPriority() throws JMSException {
        return 0;
    }

    @Override
    public void setTimeToLive(long timeToLive) throws JMSException {

    }

    @Override
    public long getTimeToLive() throws JMSException {
        return 0;
    }

    @Override
    public void setDeliveryDelay(long deliveryDelay) throws JMSException {

    }

    @Override
    public long getDeliveryDelay() throws JMSException {
        return 0;
    }

    @Override
    public Destination getDestination() throws JMSException {
        return null;
    }

    @Override
    public void close() throws JMSException {

    }

    @Override
    public void send(Message message) throws JMSException {
        try {
            if (message instanceof SLTextMessage) {
                out.writeObject(message);
                out.flush();
            }
        } catch (IOException e) {
            throw new JMSException(e.getMessage());
        }
    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }

    @Override
    public void send(Message message, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Destination destination, Message message, int deliveryMode, int priority, long timeToLive, CompletionListener completionListener) throws JMSException {

    }

    @Override
    public void send(Queue queue, Message message) throws JMSException {

    }

    @Override
    public void send(Queue queue, Message message, int deliveryMode, int priority, long timeToLive) throws JMSException {

    }
}
