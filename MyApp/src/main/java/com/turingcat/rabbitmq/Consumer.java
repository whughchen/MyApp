/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 14:38:05 
 */
package com.turingcat.rabbitmq;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
import com.rabbitmq.client.QueueingConsumer;  
  
public class Consumer {  
    private final static String QUEUE_NAME = "hello";  
  
    public static void main(String[] argv) throws Exception {  
  
        ConnectionFactory factory = new ConnectionFactory();  
        factory.setHost("172.16.35.173");  
        Connection connection = factory.newConnection();  
        Channel channel = connection.createChannel();  
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");  
  
        QueueingConsumer consumer = new QueueingConsumer(channel);  
        channel.basicConsume(QUEUE_NAME, true, consumer);  
  
        while (true) {  
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();  
            String message = new String(delivery.getBody());  
            System.out.println(" Received '" + message + "'");  
        }  
    }  
}  
