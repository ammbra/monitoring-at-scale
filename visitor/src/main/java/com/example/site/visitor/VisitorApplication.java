package com.example.site.visitor;

import io.opentracing.contrib.metrics.micrometer.MicrometerMetricsReporter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class VisitorApplication {

	public static void main(String[] args) {
		SpringApplication.run(VisitorApplication.class, args);
	}

	@Bean
	public io.opentracing.Tracer tracer() {
		return io.opentracing.contrib.metrics.Metrics.decorate(
				io.opentracing.contrib.tracerresolver.TracerResolver.resolveTracer(),
				MicrometerMetricsReporter.newMetricsReporter()
						.build());
	}

}
