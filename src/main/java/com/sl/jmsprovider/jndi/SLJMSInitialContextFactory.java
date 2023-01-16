package com.sl.jmsprovider.jndi;

import com.sl.jmsprovider.jms.SLQueue;
import com.sl.jmsprovider.jms.SLQueueConnectionFactory;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class SLJMSInitialContextFactory implements InitialContextFactory {
    private Map<String, Map> initialContextFactoryMap;
    private String connectionFactoryPrefix = "connectionFactory.";
    private String queuePrefix = "queue.";

    @Override
    public Context getInitialContext(Hashtable<?, ?> environment) throws NamingException {
        if ((initialContextFactoryMap == null) || (initialContextFactoryMap.get((String) environment.get(Context.INITIAL_CONTEXT_FACTORY)) == null)) {
            this.initialContextFactoryMap = new ConcurrentHashMap<>();
            Map map = new ConcurrentHashMap<>();

            for (Object key : environment.keySet()) {
                System.out.println("Key = " + key + " :  value = " + environment.get(key));

                String keyString = key.toString();

                if (keyString.startsWith(connectionFactoryPrefix)) {
                    map.put(keyString.substring(connectionFactoryPrefix.length()),
                            new SLQueueConnectionFactory(Integer.parseInt((String) (environment.get(key)))));
                } else if (keyString.startsWith(queuePrefix)) {
                    map.put(keyString.substring(queuePrefix.length()), new SLQueue((String) environment.get(key)));
                } else if (keyString.equals(Context.INITIAL_CONTEXT_FACTORY)) {
                    initialContextFactoryMap.put((String) environment.get(key), map);
                } else {
                    System.out.println("No such property");
                }
            }
        }

        return new SLContext(initialContextFactoryMap.get((String) environment.get(Context.INITIAL_CONTEXT_FACTORY)));
    }
}
