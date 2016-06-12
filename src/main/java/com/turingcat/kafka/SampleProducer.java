/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 18:27:00 
 */
package com.turingcat.kafka;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.util.*;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import kafka.message.Message;
import kafka.producer.KeyedMessage;
import kafka.producer.ProducerConfig;  
import kafka.javaapi.producer.Producer;  

  
public class SampleProducer {  
	static{
		Logger.getLogger("kafka").setLevel(Level.INFO);
		Logger.getLogger("org.apache.zookeeper").setLevel(Level.INFO);		
	}
  
    public static void main(String[] args) {  
    	SampleProducer ps = new SampleProducer();  
  
        Properties props = new Properties();  
        props.put("metadata.broker.list", "172.16.35.16:9092"); 
        props.put("serializer.class", "kafka.serializer.StringEncoder");  
  
        ProducerConfig config = new ProducerConfig(props);  
        Producer<String, String> producer = new Producer<String, String>(config);  
        KeyedMessage<String, String> data = new KeyedMessage<String, String>("test-topic", "test-message2");  
        for (int i = 0; i < 100; i++) {
            producer.send(data); 
            try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
            System.out.println("send : "+data);
		}
 
        producer.close();  
    }  
    

} 
