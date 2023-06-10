package com.demo.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {
	Logger logger = LoggerFactory.getLogger(MessageProducer.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void send(String topic, String payload) {
		logger.info("sending payload='{}' to topic='{}'", payload, topic);
        kafkaTemplate.send(topic, payload);
    }
}
