package com.example.site.visitor.metric;

import io.opentracing.contrib.metrics.MetricLabel;
import io.opentracing.contrib.metrics.label.BaggageMetricLabel;
import io.opentracing.contrib.metrics.label.ConstMetricLabel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {
    @Bean
    public MetricLabel versionLabel() {
        return new ConstMetricLabel("version", System.getenv("VERSION"));
    }

}
