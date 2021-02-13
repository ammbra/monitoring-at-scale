package com.example.site.itinerary;

import com.example.site.itinerary.metric.CustomMetricLabel;
import io.opentracing.contrib.metrics.micrometer.MicrometerMetricsReporter;
import io.opentracing.contrib.metrics.Metrics;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class ItineraryApplication {

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	@Value("${spring.application.name}")
	private String applicationName;

	public static void main(String[] args) {
		SpringApplication.run(ItineraryApplication.class, args);
	}
	@Bean
	public io.opentracing.Tracer tracer() {
		MicrometerMetricsReporter reporter = MicrometerMetricsReporter.newMetricsReporter()
				.withConstLabel(CustomMetricLabel.APPLICATION.getKey(), applicationName)
				.build();

		return Metrics.decorate(io.opentracing.contrib.tracerresolver.TracerResolver.resolveTracer(), reporter);
	}
}
