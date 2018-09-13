package com.mingli.kafka;

import com.mingli.kafka.config.ProducerProperties;
import com.mingli.kafka.producer.UserProducer;

public class MainClass {

	public static void main(String[] args) {
		UserProducer producer = new UserProducer(ProducerProperties.TOPIC_USER);
		producer.run();
	}

}
