package com.mingli.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import com.mingli.kafka.config.ConsumerProperties;

/**
 * @Title SimpleConsumer.java
 * @Package com.mingli.kafka.consumer
 * @author yuehui
 * @time 2018-09-13 3:56:02 PM
 * @version v1.0
 * @Description 简单的cunsumer，将接收到的消息打印出来
 */
public class SimpleConsumer{
	
	public static void main(String[] args) {
		SimpleConsumer consumer = new SimpleConsumer("test-group", ConsumerProperties.TOPIC);
		consumer.run();
	}
	
	private String groupID;
	private String topic;
	private KafkaConsumer<String, String> consumer;
	
	public SimpleConsumer(String groupID, String topic){
		super();
		this.groupID = groupID;
		this.topic = topic;
	
		Properties props = new Properties();
		props.put(ConsumerProperties.BOOTSTRAP_SERVERS, ConsumerProperties.BOOTSTRAP_SERVERS_VALUE);
		props.put(ConsumerProperties.GROOP_ID, this.groupID);
		props.put(ConsumerProperties.ENABLE_AUTO_COMMIT, ConsumerProperties.ENABLE_AUTO_COMMIT_VALUE);
		props.put(ConsumerProperties.AUTO_COMMIT_INTERVAL_MS, ConsumerProperties.AUTO_COMMIT_INTERVAL_MS_VALUE);
		props.put(ConsumerProperties.AUTO_OFFSET_RESET, ConsumerProperties.AUTO_OFFSET_RESET_VALUE);
		props.put(ConsumerProperties.KEY_DEDESERIALIZER_CLASS, ConsumerProperties.KEY_DEDESERIALIZER_CLASS_STRING);
		props.put(ConsumerProperties.VALUE_DESERIALIZER_CLASS, ConsumerProperties.VALUE_DESERIALIZER_CLASS_STRING);
		
		this.consumer = new KafkaConsumer<>(props);
		consumer.subscribe(Arrays.asList(this.topic)); //订阅topic
	}
	
	public void run() {
		try {
			while(true) {
				ConsumerRecords<String, String> records = consumer.poll(1000);
				for (ConsumerRecord<String, String> record : records) {
					System.out.printf("offset=%d, key=%s, value=%s%n", record.offset(), record.key(), record.value());
				}
			}
		}
		catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println(e.getMessage());
			System.out.println(e.toString());
		}
		finally {
			consumer.close();
		}
	}
	
}
