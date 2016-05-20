/*
 * Copyright 2016 turingcat.com
 * All right reserved.
 * @author Chen Guanghua E-mail: richard@turingcat.com
 * Created：18 May 2016 18:46:47 
 */
package com.turingcat.kafka;


/**
 * @author Chen Guanghua (richard@turingcat.com)
 *
 */
import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

public class DemoPartitioner implements Partitioner {
    public DemoPartitioner(VerifiableProperties props) {

    }

    @Override
    public int partition(Object obj, int numPartitions) {
        int partition = 0;
        if (obj instanceof String) {
            String key=(String)obj;
            int offset = key.lastIndexOf('.');
            if (offset > 0) {
                partition = Integer.parseInt(key.substring(offset + 1)) % numPartitions;
            }
        }else{
            partition = obj.toString().length() % numPartitions;
        }
        
        return partition;
    }

}