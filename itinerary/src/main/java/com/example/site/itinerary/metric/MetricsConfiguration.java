package com.example.site.itinerary.metric;

import io.opentracing.contrib.metrics.MetricLabel;
import io.opentracing.contrib.metrics.label.BaggageMetricLabel;
import io.opentracing.contrib.metrics.label.ConstMetricLabel;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MetricsConfiguration {
    @Bean
    public MetricLabel findLabel() {
        return new BaggageMetricLabel(CustomMetricLabel.FIND.getKey(), CustomMetricLabel.FIND.getValue());
    }

    @Bean
    public MetricLabel versionLabel() {
        return new ConstMetricLabel(CustomMetricLabel.VERSION.getKey(), CustomMetricLabel.VERSION.getValue());
    }

}
