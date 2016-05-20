/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 17:32:33 
 */
package com.turingcat.kafka;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import kafka.consumer.Consumer;
import kafka.consumer.ConsumerConfig;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;

/**
 * 接收数据
 * 接收到: message: 10
 */
public class kafkaConsumer1 extends Thread{
	static{
		Logger.getLogger("kafka").setLevel(Level.INFO);
		Logger.getLogger("org.apache.zookeeper").setLevel(Level.INFO);		
	}

	private String topic;
	
	public kafkaConsumer1(String topic){
		this.topic = topic;
	}
	
	
	@Override
	public void run() {
		ConsumerConnector consumer = createConsumer();
		Map<String, Integer> topicCountMap = new HashMap<String, Integer>();
		topicCountMap.put(topic, 1); // 一次从主题中获取一个数据
		 Map<String, List<KafkaStream<byte[], byte[]>>>  messageStreams = consumer.createMessageStreams(topicCountMap);
		 KafkaStream<byte[], byte[]> stream = messageStreams.get(topic).get(0);// 获取每次接收到的这个数据
		 ConsumerIterator<byte[], byte[]> iterator =  stream.iterator();
		 while(iterator.hasNext()){
			 String message = new String(iterator.next().message());
			 System.out.println("接收到: " + message);
		 }
	}

	private ConsumerConnector createConsumer() {
		Properties properties = new Properties();
		properties.put("zookeeper.connect", "172.16.35.16:2181");//声明zk
		properties.put("group.id", "group1");// 必须要使用别的组名称， 如果生产者和消费者都在同一组，则不能访问同一组内的topic数据
		return Consumer.createJavaConsumerConnector(new ConsumerConfig(properties));
	 }
	
	
	public static void main(String[] args) {
		new kafkaConsumer1("test").start();// 使用kafka集群中创建好的主题 test 
		
	}
	 
}

