/**
 * 
 */
package com.mingli.kafka.producer;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.mingli.kafka.intfacor.IProducer;

/**
 * @Title DemoCallBack.java
 * @Package com.mingli.kafka.producer
 * @author yuehui
 * @time 2018-09-14 2:09:34 PM
 * @version v1.0
 * @Description TODO
 */
public class DemoCallBack implements Callback {
    private final long startTime;
    private final String key;
    private final String message;
    private final IProducer target;
    public DemoCallBack(long startTime, String key, String message, IProducer target) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
        this.target = target;
    }
    public void onCompletion(RecordMetadata metadata, Exception exception) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        if (metadata != null) {
            System.out.println(
                "message(" + key + ", " + message + ") sent to partition(" + metadata.partition() +
                    "), " +
                    "offset(" + metadata.offset() + ") in " + elapsedTime + " ms");
        } else {
            exception.printStackTrace();
            target.close();
        }
    }
}
    
