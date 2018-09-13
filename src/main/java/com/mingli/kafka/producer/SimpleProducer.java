package com.mingli.kafka.producer;
/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import com.mingli.kafka.config.ProducerProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @Title SimpleProducer.java
 * @Package com.mingli.kafka.producer
 * @author yuehui
 * @time 2018-09-13 3:46:58 PM
 * @version v1.0
 * @Description 简单producer发送String消息到Kafka
 */
public class SimpleProducer extends Thread {
	
	public static void main(String[] args) throws Throwable {
		SimpleProducer producer = new SimpleProducer(ProducerProperties.TOPIC, false);
		producer.run();
	}

    private final KafkaProducer<String, String> producer;
    private final String topic;
    private final Boolean isAsync;

    /**
     * 初始化Producer
     * @param topic
     * @param isAsync 是否异步发送
     */
    public SimpleProducer(String topic, Boolean isAsync) {
        Properties props = new Properties();
        props.put(ProducerProperties.BOOTSTRAP_SERVERS, ProducerProperties.BATCH_SIZE_VALUE);
        props.put(ProducerProperties.ACKS, ProducerProperties.ACKS_VALUE);
        props.put(ProducerProperties.KEY_SERIALIZER_CLASS, ProducerProperties.KEY_SERIALIZER_CLASS_STRING);
        props.put(ProducerProperties.VALUE_SERIALIZER_CLASS, ProducerProperties.VALUE_SERIALIZER_CLASS_STRING);
        //构建拦截链
        List<String> inteceptors = new ArrayList<>();
        inteceptors.add(ProducerProperties.INTERCEPTOR_CLASS_TIMESTAMP);
        inteceptors.add(ProducerProperties.INTERCEPTOR_CLASS_COUNTER);
        props.put(ProducerProperties.INTERCEPTOR_CLASSES, inteceptors);
        producer = new KafkaProducer<>(props);
        this.topic = topic;
        this.isAsync = isAsync;
    }

    public void run() {
        int messageNo = 1;
        while (messageNo < 40) {
            String messageStr = "Message_" + messageNo;
            long startTime = System.currentTimeMillis();
            if (isAsync) { // Send asynchronously
                producer.send(new ProducerRecord<>(topic,
                    Integer.toString(messageNo),
                    messageStr), new DemoCallBack(startTime, messageNo, messageStr));
            } else { 
                try {
                    producer.send(new ProducerRecord<>(topic,
                        Integer.toString(messageNo),
                        messageStr)).get();
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
            ++messageNo;
        }
    }
    public void close() {
        producer.close();
    }
}
class DemoCallBack implements Callback {
    private final long startTime;
    private final int key;
    private final String message;
    public DemoCallBack(long startTime, int key, String message) {
        this.startTime = startTime;
        this.key = key;
        this.message = message;
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
        }
    }
    
}
