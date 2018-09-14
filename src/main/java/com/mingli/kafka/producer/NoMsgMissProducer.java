package com.mingli.kafka.producer;

import java.util.Properties;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import com.mingli.kafka.config.ProducerProperties;
import com.mingli.kafka.intfacor.IProducer;

/**
 * @Title NoMsgMissProducer.java
 * @Package com.mingli.kafka.producer
 * @author yuehui
 * @time 2018-09-14 11:42:31 AM
 * @version v1.0
 * @Description 
 * <h2>无消息丢失异步实现</h2> </br>
 * <h4>在broker端需注意的是：</h4></br>
 * <li>1. unclean.leader.election.enable=false  关闭unclean leader选举， 不允许非ISR中的副本被选举为leader</br>
 * <li>2. replication.factor >= 3  三备份原则，使用多个副本来保存分区消息</br>
 * <li>3. min.insync.replicas > 1  用于控制某条消息至少被写入到ISR中的多少个副本才算成功，producer端acks为all/-1,这个参数才有效</br>
 * <li>4. replication.factor > min.insync.replicas， 推荐replication.factor = min.insync.replicas + 1</br>
 */
public class NoMsgMissProducer extends Thread implements IProducer{

    private final KafkaProducer<String, String> producer;
    private final String topic;
	
    public NoMsgMissProducer(String topic) {
    	Properties props = new Properties();
        props.put(ProducerProperties.BOOTSTRAP_SERVERS, ProducerProperties.BOOTSTRAP_SERVERS_VALUE);
       
        props.put(ProducerProperties.KEY_SERIALIZER_CLASS, ProducerProperties.KEY_SERIALIZER_CLASS_STRING);
        props.put(ProducerProperties.VALUE_SERIALIZER_CLASS, ProducerProperties.VALUE_SERIALIZER_CLASS_STRING);
        /**
         * 实现无消息丢失producer端配置
         */
        props.put(ProducerProperties.ACKS, "all");
        props.put(ProducerProperties.RETRIES, Integer.toString(Integer.MAX_VALUE)); //设置成MAX_VALUE虽然有些极端，等价于producer开启无限重试（当然只会重拾那些可恢复的异常）
        props.put(ProducerProperties.MAX_IN_FLIGHT_REQUESTS_PER_, Integer.toString(1));//producer在某个broker发送响应之前将无法在给该broker发送PRODUCE请求
        
        producer = new KafkaProducer<>(props);
        this.topic = topic;
    }

    /**
     * @time 2018-09-14 2:10:53 PM
     * @Title run   
     * @Description 实现异步发送，使用带有回调机制的send  
     * @return void
     */
    public void run0() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo + ", CallBack";
            producer.send(new ProducerRecord<>(topic,
                Integer.toString(messageNo),
                messageStr), 
            	new Callback() {
					@Override
					public void onCompletion(RecordMetadata metadata, Exception exception) {
						if(exception == null) {
							//消息发送成功
							System.out.println("SUCCESS send message," + metadata.timestamp() + "," + metadata.partition());
						} else {
							//执行错误逻辑
							exception.printStackTrace();
							close(); //关闭producer
						}
					}
				});
            ++messageNo;
        }
    }
    
    /**
     * @time 2018-09-14 2:10:53 PM
     * @Title run   
     * @Description 实现异步发送，使用带有回调机制（自定义）的send  
     * @return void
     */
    public void run() {
        int messageNo = 1;
        while (true) {
            String messageStr = "Message_" + messageNo + ", CallBack Custom";
            long startTime = System.currentTimeMillis();
			producer.send(new ProducerRecord<>(topic,
			    Integer.toString(messageNo),
			    messageStr), new DemoCallBack(startTime, Integer.toString(messageNo), messageStr, this));
            ++messageNo;
        }
    }
    public void close() {
        producer.close();
    }
}

