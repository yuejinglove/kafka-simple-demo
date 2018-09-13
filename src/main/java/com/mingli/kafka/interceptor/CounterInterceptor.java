package com.mingli.kafka.interceptor;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
/**
 * @Title CounterInterceptor.java
 * @Package com.mingli.kafka.interceptor
 * @author yuehui
 * @time 2018-09-13 3:02:39 PM
 * @version v1.0
 * @Description 拦截器类，实现package org.apache.kafka.clients.producer.ProducerInterceptor接口
 *   onSend(ProducerRecord) : 该方法封装进KafkaProducer.send方法中， 
 *   			producer确保在消息被序列化前调用该方法，用户可以在该方法中对消息做任何操作，
 *   			但最好不要修改消息所属的topic和partition
 *   onAcknowledgement(RecordMetadata, Exception) : 该方法会在消息被应答或消息发送失败时调用，并且通常在producer回调逻辑触发之前。
 *   			该方法运行在producer的I/O线程中，因此不要在该方法中放较“重”的逻辑，否则会拖慢producer的发送效率
 *   close : 关闭interception,关闭producer时执行，主要作用于资源清理工作
 */
public class CounterInterceptor implements ProducerInterceptor<String, Object>{
	private static int errorCounter = 0;
	private static int successCounter = 0;
	@Override
	public void configure(Map<String, ?> configs) {
		
	}
	@Override
	public ProducerRecord<String, Object> onSend(ProducerRecord<String, Object> record) {
		return record;
	}
	@Override
	public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
		if(exception == null) {
			successCounter++;
		}else {
			errorCounter++;
		}
	}
	@Override
	/**
	 * @author yuehui
	 * 在producer close的时候执行
	 */
	public void close() {
		System.out.println("success:" + successCounter + ", error:" + errorCounter);
	}
	
	
	
}
