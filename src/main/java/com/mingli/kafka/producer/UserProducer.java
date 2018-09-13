package com.mingli.kafka.producer;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import com.mingli.kafka.config.ProducerProperties;
import com.mingli.kafka.pojo.User;

public class UserProducer extends Thread {
    private final KafkaProducer<String, User> producer;
    private final String topic;

    public UserProducer(String topic) {
        Properties props = new Properties();
        props.put(ProducerProperties.BOOTSTRAP_SERVERS, ProducerProperties.BOOTSTRAP_SERVERS_VALUE);
        props.put(ProducerProperties.ACKS, ProducerProperties.ACKS_VALUE);
        props.put(ProducerProperties.KEY_SERIALIZER_CLASS, ProducerProperties.KEY_SERIALIZER_CLASS_STRING);
        props.put(ProducerProperties.VALUE_SERIALIZER_CLASS, ProducerProperties.VALUE_SERIALIZER_CLASS_USER);
        //构建拦截链
        List<String> inteceptors = new ArrayList<>();
//        inteceptors.add(ProducerProperties.INTERCEPTOR_CLASS_TIMESTAMP);
        inteceptors.add(ProducerProperties.INTERCEPTOR_CLASS_COUNTER);
        props.put(ProducerProperties.INTERCEPTOR_CLASSES, inteceptors);
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    public void run() {
        int messageNo = 1;
        while (messageNo < 20) {
        	User user = new User("kk", "ll", messageNo, "ZK");
            String messageStr = "Message_" + messageNo;
            try {
            	ProducerRecord<String, User> record = new ProducerRecord<>(topic, messageStr, user);
                producer.send(record).get();
                System.out.println("Sent message: (" + messageNo + ", " + record.toString() + ")");
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            ++messageNo;
        }
    }
    public void close() {
        producer.close();
    }
    
    
	public static void main(String[] args) throws Throwable {
			UserProducer producer = new UserProducer(ProducerProperties.TOPIC_USER);
			producer.run();
			producer.clone();
		}
}
