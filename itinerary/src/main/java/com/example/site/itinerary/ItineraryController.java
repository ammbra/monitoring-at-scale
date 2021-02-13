package com.example.site.itinerary;

import com.example.site.itinerary.dto.ItineraryDTO;
import com.example.site.itinerary.metric.CustomMetricLabel;
import io.micrometer.core.instrument.MeterRegistry;
import io.opentracing.Scope;
import io.opentracing.Tracer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
public class ItineraryController {
    private ItineraryService itineraryService;

    private Tracer tracer;

    @Value("${testFlagEnabled}")
    private boolean testFlagEnabled;

    @Autowired
    public ItineraryController(ItineraryService itineraryService, Tracer tracer) {
        this.itineraryService = itineraryService;
        this.tracer = tracer;
    }

    @GetMapping({"/itinerary"})
    public ItineraryDTO getItinerary() throws InterruptedException {
        Optional.ofNullable(tracer.activeSpan()).ifPresent(as -> as.setBaggageItem("find", "itinerary"));
        if (Math.random() > 0.8 && !testFlagEnabled) {
            Thread.sleep(1 + (long)(Math.random()*500));
            throw new RuntimeException("Failed to find itinerary");
        }
        try (Scope scope = tracer.buildSpan("SomeItinerary").startActive(true)) {
            scope.span().setTag("find", "itinerary");
            return this.itineraryService.getRandom();
        }
    }


}
