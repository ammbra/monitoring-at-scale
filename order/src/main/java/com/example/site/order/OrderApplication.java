package com.example.site.order;

import com.example.site.order.metric.CustomMetricLabel;
import io.opentracing.contrib.metrics.micrometer.MicrometerMetricsReporter;
import io.opentracing.contrib.metrics.prometheus.spring.autoconfigure.PrometheusMetricsReporterConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class OrderApplication {

	@Value("${spring.application.name}")
	private String applicationName;

	PrometheusMetricsReporterConfiguration reporterConfiguration;

	@Bean
	public io.opentracing.Tracer tracer() {
		return io.opentracing.contrib.metrics.Metrics.decorate(
				io.opentracing.contrib.tracerresolver.TracerResolver.resolveTracer(),
				MicrometerMetricsReporter.newMetricsReporter()
						.withBaggageLabel(CustomMetricLabel.TRANSACTION.getKey(), CustomMetricLabel.TRANSACTION.getValue())
						.withConstLabel(CustomMetricLabel.APPLICATION.getKey(), applicationName).build());
	}
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
	}
}
