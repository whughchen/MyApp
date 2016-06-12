/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 18:45:34 
 */
package com.turingcat.kafka;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;

public class DemoConsumerMsgTask implements Runnable {
    private KafkaStream m_stream;
    private int m_threadNumber;

    public DemoConsumerMsgTask(KafkaStream stream, int threadNumber) {
        m_threadNumber = threadNumber;
        m_stream = stream;
    }

    public void run() {
        ConsumerIterator<byte[], byte[]> it = m_stream.iterator();
        while (it.hasNext())
            System.out.println("Thread " + m_threadNumber + ": Received "
                    + new String(it.next().message()));
        System.out.println("Shutting down Thread: " + m_threadNumber);
    }
}