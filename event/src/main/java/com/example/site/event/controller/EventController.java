package com.example.site.event.controller;

import com.example.site.event.dto.EventDTO;
import com.example.site.event.service.EventService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class EventController {

    @Autowired
    private EventService eventService;

    Counter eventCounter;

    public EventController(MeterRegistry registry) {
        eventCounter = Counter.builder("event_counter")
                .description("Number of events accessed")
                .register(registry);
    }


    @Timed(value = "get.event.requests", histogram = true, percentiles = { 0.95, 0.99 }, extraTags = { "version",
            "v1" })
    @GetMapping("/event")
    public EventDTO getRandomEvent() throws InterruptedException {
        eventCounter.increment();
        Random random = new Random();
        return this.eventService.getByIndex(random.ints(0, 4).findFirst().getAsInt());
    }

    @GetMapping("/events")
    public double eventCount() {
        return eventCounter.count();
    }

}
