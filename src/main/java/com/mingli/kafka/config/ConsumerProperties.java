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
 * @Description KafkaConsumer类的配置参数
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
	
	
	/*
	 * 指定consumer是否自动提交位移。
	 * true：consumer在后台自动提交offset。
	 * false： 用户手动提交offset。
	 */
	public static final String ENABLE_AUTO_COMMIT = ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG;
	public static final String ENABLE_AUTO_COMMIT_VALUE = "true";

	/*
	 * consumer提交offset的频率，单位是ms
	 * 使用该参数前提是 enable.auto.commit 为 true
	 */
	public static final String AUTO_COMMIT_INTERVAL_MS = ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG;
	public static final String AUTO_COMMIT_INTERVAL_MS_VALUE = Integer.toString(60*1000);
	
	/*
	 * 制定无位移信息或位移越界（既consumer要消费的信息的位移不在当前消息日志的合理区域范围）时Kafka的应对策略
	 * earliest： 指定从最早的位移开始消费。注意这里最早的位移不一定是0
	 * latest： 指定从最新处位移开始消费
	 * none： 指定如果发生位移信息或越界位移，则抛出异常。（该值在真实业务中使用甚少）
	 */
	public static final String AUTO_OFFSET_RESET = ConsumerConfig.AUTO_OFFSET_RESET_CONFIG;
	public static final String AUTO_OFFSET_RESET_VALUE = "earliest";
	
	
	public static final String REQUEST_TIMEOUT_MS = ConsumerConfig.REQUEST_TIMEOUT_MS_CONFIG;
	public static final String REQUEST_TIMEOUT_MS_VALUE = Integer.toString(60*1000);  	// 60000ms
	
	/*
	 * 推荐设置一个较低的值，让group下的其他成员能够更快感知新一轮rebalance开启了。
	 * 必须小于 session.timeout.ms 。
	 */
	public static final String HEARTBEAT_INTERVAL_MS = ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG;
	public static final String HEARTBEAT_INTERVAL_MS_VALUE = Integer.toString(5*1000);  	// 60000ms
	
	/*
	 * 控制单次poll调用返回的最大消息数。（默认500）
	 * 比较极端的做法是将该参数配置为1,那么每次poll只会返回1条消息。
	 * 如果用户发现consumer端的瓶颈在poll速度太慢，则可以适当地增加该参数的值。
	 * 如果用户的消息处理逻辑很轻量，默认的500条消息通常不能满足实际的消息处理速度。
	 */
	public static final String MAX_POLL_RECORDS = ConsumerConfig.MAX_POLL_RECORDS_CONFIG;
	public static final String MAX_POLL_RECORDS_VALUE = Integer.toString(1000);  	// 60000ms
	
	/*
	 * 指定了consumer端单次获取数据的最大字节数。
	 * 若实际业务消息很大，则必须要设置参数为一个较大的值，否则consumer将无法消费这些消息。
	 */
	public static final String FETCH_MAX_BYTES = ConsumerConfig.FETCH_MAX_BYTES_CONFIG;
	public static final String FETCH_MAX_BYTES_VALUE = Integer.toString(1000);  	// 60000ms
	
	/*
	 * session.timeout.ms 是consumer group检查组内成员发生崩溃的时间，当某个成员崩溃/宕机，
	 * 管理group的kafka组件需要该参数值的时间才能感知到
	 * 同时，该参数还有另一重含义：consumer消息处理逻辑的最大时间，若consumer两次poll之间的间隔超过了该参数所设的阈值，
	 * 那么coordinator就会认为这个cinsumer已经追不上组内其他成员，将该consumer实例提出组。
	 * 默认值 10s
	 */
	public static final String SESSION_TIMEOUT_MS = ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG;
	public static final String SESSION_TIMEOUT_MS_VALUE = Integer.toString(10*1000);
	
	/*
	 * 用来设置消息处理逻辑的最大时间。
	 * 假设用户的业户场景中消息处理逻辑是把消息“落地”到远程数据库中，
	 * 且这个过程平均处理时间是2分钟，则需要将该参数指设置为大于2分钟的值
	 */
	public static final String MAX_POLL_INTERVAL_MS = ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG;
	public static final String MAX_POLL_INTERVAL_VALUE = Integer.toString(2*60*1000);
	
	/*
	 * Kafka会定期的关闭空闲Socket连接，导致下次onsumer处理请求时需要重新创建向broker的Socket连接，影响请求平均处理时间。
	 * 默认值 9分钟
	 * 如果用户时间环境中不在乎这些重新创建的Socket资源开销，推荐设置为-1, 既不需要关闭这些空闲连接。
	 */
	public static final String CONNECTIONS_MAX_IDLE_MS = ConsumerConfig.CONNECTIONS_MAX_IDLE_MS_CONFIG;
	public static final String CONNECTIONS_MAX_IDLE_MS_VALUE = Integer.toString(2*60*1000);
	
	private ConsumerProperties() {}
}
