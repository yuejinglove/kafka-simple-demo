package com.mingli.kafka.serializer;

import java.util.Map;
import org.apache.kafka.common.serialization.Serializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title UserSerializer.java
 * @Package com.mingli.kafka.serializer
 * @author yuehui
 * @time 2018-09-13 3:49:43 PM
 * @version v1.0
 * @Description User 的序列化类，根据Pojo类的getter 方法序列化成json格式String
 */
public class UserSerializer implements Serializer{
	
	private ObjectMapper objectMapper;
	private Logger logger = LoggerFactory.getLogger(UserSerializer.class);
	
	@Override
	public void configure(Map configs, boolean isKey) {
		objectMapper = new ObjectMapper();
	}

	@Override
	public byte[] serialize(String topic, Object data) {
		byte[] ret = null;
		try {
			ret = objectMapper.writeValueAsString(data).getBytes("utf-8");
		}catch (Exception e) {
			logger.warn("failed to serializer the object:{}", data, e);
		}
		return ret;
	}

	@Override
	public void close() {
	}
	
}
