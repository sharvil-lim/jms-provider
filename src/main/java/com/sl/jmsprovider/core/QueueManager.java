package com.sl.jmsprovider.core;

import javax.jms.Message;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueManager {
    private static QueueManager instance;
    private static ConcurrentHashMap<String, BlockingQueue<Message>> queuePool;

    private QueueManager() {
        queuePool = new ConcurrentHashMap<>();
    }

    public static QueueManager instantiate() {
        if (instance == null) {
            instance = new QueueManager();
        } else if (queuePool == null) {
            instance = new QueueManager();
        }

        return instance;
    }

    public void makeQueue(String queueName) {
        BlockingQueue<Message> messageQueue = new LinkedBlockingQueue<>();
        queuePool.put(queueName, messageQueue);
    }

    public BlockingQueue<Message> getQueue(String queueName) {
        return queuePool.get(queueName);
    }

    public boolean hasQueue(String queueName) {
        return queuePool.containsKey(queueName);
    }
}
