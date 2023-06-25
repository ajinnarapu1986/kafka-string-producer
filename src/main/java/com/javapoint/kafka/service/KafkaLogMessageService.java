package com.javapoint.kafka.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.javapoint.kafka.AppConstants;

@Service
public class KafkaLogMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaLogMessageService.class);

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(List<String> logMessages) {
		LOGGER.info(String.format("Message sent -> %s", logMessages.size()));

		logMessages.forEach(logMessage -> {
			LOGGER.info("I am a Kafka Producer :: {}", logMessage);

			kafkaTemplate.send(AppConstants.TOPIC_RAW_DATA_NAME, logMessage);
		});

	}
}

