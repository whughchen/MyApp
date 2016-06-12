/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 14:36:38 
 */
package com.turingcat.rabbitmq;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;  
import com.rabbitmq.client.Connection;  
import com.rabbitmq.client.ConnectionFactory;  
  
public class Producer {  
    private final static String QUEUE_NAME = "test2";  
  
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {  
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("172.16.35.173"); //172.16.35.173 //120.24.91.83
        factory.setUsername("guest");
        factory.setPassword("cooxm");
        Connection connection = factory.newConnection(); 
        Channel channel = connection.createChannel();  
  
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);  
        String message = "Hello World ";  
        for (int i = 0; i < 100; i++) {
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());  
            System.out.println("Sent [" + i +"] "+ message + i);  

			Thread.sleep(1000);

		}

  
        channel.close();  
        connection.close();  
    }  
}