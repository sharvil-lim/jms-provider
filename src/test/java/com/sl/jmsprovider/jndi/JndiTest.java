package com.sl.jmsprovider.jndi;

import com.sl.jmsprovider.jms.SLQueueConnectionFactory;
import org.junit.jupiter.api.Test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.jupiter.api.Assertions.*;

class JndiTest {

    @Test
    void testContext() {
        Map map = new ConcurrentHashMap<>();
        map.put("anyone", "name");
        SLContext context = new SLContext(map);
        try {
            assertEquals("name", context.lookup("anyone"));
            assertNotEquals("name", context.lookup("anything"));
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testInitialContext() throws NamingException {
        Hashtable<Object, Object> hashTable = new Hashtable();
        hashTable.put("anyone", "name");
        SLJMSInitialContextFactory factory = new SLJMSInitialContextFactory();
        Context ctx = factory.getInitialContext(hashTable);
        assertNotNull(ctx);
        assertEquals("name", ctx.lookup("anyone"));
    }

    @Test
    void testJndiWithPropertyFile() {

        try {
            InitialContext ctx = new InitialContext();
            assertInstanceOf(SLQueueConnectionFactory.class, ctx.lookup("ConnectionFactory"));
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void testJndi() {
        Properties p = new Properties();
        p.setProperty(Context.INITIAL_CONTEXT_FACTORY, "com.sl.jmsprovider.jndi.SLJMSInitialContextFactory");
        p.setProperty("connectionFactory.ConnectionFactory", "1234");
        p.setProperty("queue.sampleQueue", "Sample");

        try {
            InitialContext ctx = new InitialContext(p);
            assertInstanceOf(SLQueueConnectionFactory.class, ctx.lookup("ConnectionFactory"));
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
