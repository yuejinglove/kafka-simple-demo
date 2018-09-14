package com.mingli.kafka;

import com.mingli.kafka.config.ConsumerProperties;
import com.mingli.kafka.config.ProducerProperties;
import com.mingli.kafka.consumer.SimpleConsumer;
import com.mingli.kafka.producer.NoMsgMissProducer;

public class MainAppllication {

	public static void main(String[] args) {
		NoMsgMissProducer producerThread = new NoMsgMissProducer(ProducerProperties.TOPIC);
		producerThread.start();
		
		SimpleConsumer simpleConsumer = new SimpleConsumer(ConsumerProperties.GROOP_ID, ConsumerProperties.TOPIC);
		simpleConsumer.start();
		
	}

}
