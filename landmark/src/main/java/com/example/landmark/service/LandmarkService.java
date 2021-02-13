package com.example.landmark.service;

import com.example.landmark.model.LandmarkDTO;
import lombok.Getter;
import org.eclipse.microprofile.opentracing.Traced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Traced
@ApplicationScoped
public class LandmarkService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LandmarkService.class);

    private Map<Integer, LandmarkDTO> landmarks;

    @PostConstruct
    private void init() {
         LOGGER.info("Starting the initialization of landmarks locations...");
         this.landmarks = new HashMap<>();
         this.landmarks.put(0, new LandmarkDTO("Default", "Default", "Default", "Default", ""));
         this.landmarks.put(1, new LandmarkDTO("National Art Museum", "Calea Victoriei 49-53", "Bucharest", "Romania",""));
         this.landmarks.put(2, new LandmarkDTO("National Gallery", "Trafalgar Square", "London", "United Kingdom",""));
         this.landmarks.put(3, new LandmarkDTO("Metropolitan Museum of Art", "1000 5th Ave", "New York", "United States",""));

         LOGGER.info("{} landmarks were detected", landmarks.size());
    }

    public LandmarkDTO getByCity(String city) {
        return landmarks.values().stream().filter(e -> e.getCity().toLowerCase().contains(city)).findFirst().orElse(landmarks.get(0));
    }
}


