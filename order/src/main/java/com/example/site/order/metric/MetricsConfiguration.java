package com.example.site.order.metric;

import io.opentracing.contrib.metrics.MetricLabel;
import io.opentracing.contrib.metrics.label.BaggageMetricLabel;
import io.opentracing.contrib.metrics.label.ConstMetricLabel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {
    @Bean
    public MetricLabel transactionLabel() {
        return new BaggageMetricLabel(CustomMetricLabel.TRANSACTION.getKey(), CustomMetricLabel.TRANSACTION.getValue());
    }

    @Bean
    public MetricLabel versionLabel() {
        return new ConstMetricLabel(CustomMetricLabel.VERSION.getKey(), System.getenv("VERSION"));
    }

}
