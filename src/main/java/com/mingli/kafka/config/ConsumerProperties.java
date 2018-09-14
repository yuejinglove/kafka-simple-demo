/**
 * 
 */
package com.mingli.kafka.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;

/**
 * @Title ConsumerProperties.java
 * @Package com.mingli.kafka.config
 * @author yuehui
 * @time 2018-09-13 5:48:38 PM
 * @version v1.0
 * @Description TODO
 */
public class ConsumerProperties {

	public static final String TOPIC = "test-topic";
	public static final String TOPIC_USER = "test-user";
	
	/*
	 * bootstrap.servers:用于初始化时建立链接到kafka集群,
	 * 以host:port形式,多个以逗号分隔host1:port1,host2:port2
	 */
	public static final String BOOTSTRAP_SERVERS = ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG;
	public static final String BOOTSTRAP_SERVERS_VALUE = "kafka1:9092,kafka2:9093,kafka3:9094";
	
	
	public static final String GROOP_ID = ConsumerConfig.GROUP_ID_CONFIG;
	/*
	 * key.DESERIALIZER, value.DESERIALIZER说明了使用何种序列化方式将用户提供的key和vaule值序列化成字节
	 */
	public static final String KEY_DEDESERIALIZER_CLASS = ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG;
	public static final String KEY_DEDESERIALIZER_CLASS_STRING = "org.apache.kafka.common.serialization.StringDeserializer";
	public static final String VALUE_DESERIALIZER_CLASS = ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG;
	public static final String VALUE_DESERIALIZER_CLASS_STRING = "org.apache.kafka.common.serialization.StringDeserializer";
	
	

	public static final String ENABLE_AUTO_COMMIT = ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
	public static final String ENABLE_AUTO_COMMIT_VALUE = "true";

	public static final String AUTO_COMMIT_INTERVAL_MS = ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG;
	public static final String AUTO_COMMIT_INTERVAL_MS_VALUE = "1000";
	
	public static final String AUTO_OFFSET_RESET = ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
	public static final String AUTO_OFFSET_RESET_VALUE = "earliest";
	

	public static final String REQUEST_TIMEOUT_MS = ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG;
	public static final Integer REQUEST_TIMEOUT_MS_VALUE = 60000;  	// 60000ms
	
	
	
	private ConsumerProperties() {}
}
