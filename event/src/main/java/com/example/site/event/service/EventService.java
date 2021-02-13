package com.example.site.event.service;


import com.example.site.event.dto.EventDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class EventService {

    private Map<Integer, EventDTO> events;


    @PostConstruct
    private void init() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.events = new HashMap<>();
        this.events.put(0, new EventDTO("Live Concert","Somewhere in Bucharest", "Bucharest", "Romania", dateFormat.format(new Date())));
        this.events.put(1, new EventDTO("Vernisage", "Somewhere in London", "London", "United Kingdom", dateFormat.format(new Date())));
        this.events.put(2, new EventDTO("Movie Picnic", "Somewhere in New York", "New York", "United States of America", dateFormat.format(new Date())));
        this.events.put(3, new EventDTO("Music Festival", "Somewhere in Bucharest", "Bucharest", "Romania", dateFormat.format(new Date())));
    }

    public EventDTO getByIndex(Integer index) {
        return events.get(index);
    }
}
