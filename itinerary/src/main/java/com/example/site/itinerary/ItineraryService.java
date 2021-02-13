package com.example.site.itinerary;


import com.example.site.itinerary.dto.ItineraryDTO;
import com.example.site.itinerary.dto.LandmarkDTO;
import com.example.site.itinerary.exception.RestTemplateResponseErrorHandler;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ItineraryService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ItineraryService.class);

    private static String landmarkUrl = System.getProperty("LANDMARK_URL", System.getenv("LANDMARK_URL"));;

    private List<ItineraryDTO> itineraries;

    private Tracer tracer;

    private RestTemplate restTemplate;

    @Autowired
    public ItineraryService(Tracer tracer, RestTemplate restTemplate) {
        this.tracer = tracer;
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    private void init() throws Exception {
        try {
            ResponseEntity<List<LandmarkDTO>> response = restTemplate.exchange(landmarkUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<LandmarkDTO>>(){});
            List<LandmarkDTO> dtos = response.getBody();
            this.itineraries = new ArrayList<>(dtos.size());
            for (int i = 0; i < dtos.size(); i++) {
                LandmarkDTO dto = dtos.get(i);
                this.itineraries.add(new ItineraryDTO(i, dto));
            }
        } catch (Exception e) {
            try (Scope scope = tracer.buildSpan("NoLandmark").startActive(true)) {
                scope.span().setTag("unavailable", "landmark");
                LOGGER.error("Failed to get landmarks", e);
            }
        }
    }

    public ItineraryDTO getRandom() {
        Random random = new Random();
        return itineraries.get(random.ints(0, itineraries.size()).findFirst().getAsInt());
    }
}
