package com.bmw.psbom.kafkaproducer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class ESIMessageProducer {

	private KafkaTemplate<String, String> kafkaTemplate;

	public ESIMessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	public void produce(final String message) {
		ListenableFuture<SendResult<String, String>> future =
				kafkaTemplate.send("bmw.psbombb.ESIResponse.v1.allplants", message);

		future.addCallback(new ListenableFutureCallback<>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				System.out.println("Sent message=[" + message + "] with offset=[" + result.getRecordMetadata().offset() + "]");
			}

			@Override
			public void onFailure(Throwable ex) {
				System.out.println("Unable to send message=[" + message + "] due to : " + ex.getMessage());
			}
		});
	}
}
