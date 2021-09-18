package br.com.pozzo.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumer {
	Logger logger = LoggerFactory.getLogger(TopicConsumer.class);

	@Value("${topic.name.consumer")
	private String topicName;

	@KafkaListener(topics = "${topic.name.consumer}", groupId = "account-balance-consumer-group")
	public void consume(ConsumerRecord<String, String> payload) {
		logger.info("key: {}", payload.key());
		logger.info("Value: {}", payload.value());
	}
}