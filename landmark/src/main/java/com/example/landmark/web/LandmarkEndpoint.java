package com.example.landmark.web;

import com.example.landmark.model.LandmarkDTO;
import com.example.landmark.service.LandmarkService;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.opentracing.Traced;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import javax.ws.rs.Produces;

import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;

@Path("landmark")
@RequestScoped
public class LandmarkEndpoint {
    private static final Logger LOGGER = LoggerFactory.getLogger(LandmarkEndpoint.class);

    @Inject
    private LandmarkService landmarkService;

    @Inject
    Tracer tracer;

    @Timeout(value = 2, unit = ChronoUnit.SECONDS)
    @Retry(maxRetries = 2, maxDuration = 2000)
    @Fallback(fallbackMethod = "fallbackService")
    @GET
    @Path("/{city}")
    @Produces(MediaType.APPLICATION_JSON)
      public LandmarkDTO getByCity(@PathParam("city") String city) throws InterruptedException {
        try (Scope scope = tracer.buildSpan("SomeLandmark").startActive(true)) {
            scope.span().setTag("landmark", "finding");
            return this.landmarkService.getByCity(city);
        }

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<LandmarkDTO> getLandmarks() throws InterruptedException {
        try (Scope scope = tracer.buildSpan("findAll").startActive(true)) {
            scope.span().setTag("landmarks", "findAll");
            return this.landmarkService.getLandmarks().values().stream().collect(Collectors.toList());
        }

    }

    public LandmarkDTO fallbackService(String address) {
        LandmarkDTO landmarkNotFound = new LandmarkDTO();
        landmarkNotFound.setMessage(String.format("A landmark for address %s could not be found.", address));
        return landmarkNotFound;
    }
}
