package com.example.projectreactor;

import com.example.projectreactor.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

@Slf4j
public class CallPersonUsingRestTemplate {

	private static final RestTemplate restTemplate = new RestTemplate();

	static {
		String baseUrl = "http://localhost:8080";
		restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(baseUrl));
	}

	public static void main(String[] args) {
		Instant start = Instant.now();
		IntStream.rangeClosed(1, 5).forEachOrdered(i -> restTemplate.getForObject("/person/{id}", Person.class, i));
		logTime(start);
	}

	private static void logTime(Instant start) {
		log.debug("Elapsed time: " + Duration.between(start, Instant.now()).toMillis() + "ms");
	}
}
