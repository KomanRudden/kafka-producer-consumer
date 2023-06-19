package com.bmw.psbom.kafkaproducer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ESIMessageController {

	private ESIMessageProducer producer;

	public ESIMessageController(ESIMessageProducer producer) {
		this.producer = producer;
	}

	@PostMapping("windchill/esi")
	public void sendESIMessage(@RequestBody String message) {
		producer.produce(message);
	}

	@GetMapping("windchill/esi")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("YAY");
	}
}
