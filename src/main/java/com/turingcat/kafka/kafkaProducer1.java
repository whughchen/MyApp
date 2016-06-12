/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 17:18:37 
 */
package com.turingcat.kafka;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.util.Properties;  
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import kafka.javaapi.producer.Producer;  
import kafka.producer.KeyedMessage;  
import kafka.producer.ProducerConfig;  
import kafka.serializer.StringEncoder;  
  
  
  
  
public class kafkaProducer1 extends Thread{  
	
	static{
		Logger mongoLogger = Logger.getLogger("kafka");
		mongoLogger.setLevel(Level.INFO);
	}
  
    private String topic;  
      
    public kafkaProducer1(String topic){  
        this.topic = topic;  
    }  
      
      
    @Override  
    public void run() {  
        Producer<Integer, String> producer = createProducer();  
        int i=0;  
        while(true){  
        	String msString="# ----------------------------             message: " + i++;
            producer.send(new KeyedMessage<Integer, String>(topic,msString ));  
            System.out.println("send : " + msString);
            try {  
                TimeUnit.SECONDS.sleep(1);  
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
  
    private Producer<Integer, String> createProducer() {  
        Properties properties = new Properties();  
        properties.put("zookeeper.connect", "172.16.35.16:2181");//声明zk  
        properties.put("serializer.class", StringEncoder.class.getName());  
        properties.put("metadata.broker.list", "172.16.35.16:9092");// 声明kafka broker  
        return new Producer<Integer, String>(new ProducerConfig(properties));  
     }  
      
      
    public static void main(String[] args) {  
        new kafkaProducer1("test").start();// 使用kafka集群中创建好的主题 test   
          
    }  
       
}  