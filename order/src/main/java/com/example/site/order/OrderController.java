package com.example.site.order;

import com.example.site.order.dto.VisitorDTO;
import io.opentracing.Scope;
import io.opentracing.Tracer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class OrderController {

    @Value("${itinerary}")
    private String itineraryUrl;

    @Value("${visitor}")
    private String visitorUrl;

    @Value("${event}")
    private String eventUrl;

    private static final String path = "/itinerary";

    private static VisitorDTO visitor;

    private RestTemplate restTemplate;

    private io.opentracing.Tracer tracer;

    @Autowired
    public OrderController(RestTemplate restTemplate, Tracer tracer) {
        this.tracer = tracer;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/buy/itinerary")
    public String buyItinerary() throws InterruptedException {
        currentVisitor();
        Optional.ofNullable(tracer.activeSpan()).ifPresent(as -> as.setBaggageItem("transaction", "buy"));
        try (Scope scope = tracer.buildSpan("BuyItinerary").startActive(true)) {
            scope.span().setTag("buy", "itinerary");
            String url = new StringBuffer().append(itineraryUrl).append(path).toString();
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return new StringBuffer().append("Visitor ").append(visitor.getName()).append(" BUYS  ").append(response.getBody()).toString();
        }
    }


    @GetMapping("/buy/event")
    public String buyEvent() throws InterruptedException {
        currentVisitor();
        Optional.ofNullable(tracer.activeSpan()).ifPresent(as -> as.setBaggageItem("transaction", "buy"));
        try (Scope scope = tracer.buildSpan("BuyEvent").startActive(true)) {
            scope.span().setTag("buy", "event");
            ResponseEntity<String> response = restTemplate.getForEntity(eventUrl, String.class);
            return new StringBuffer().append("Visitor ").append(visitor.getName()).append(" BUYS ").append(response.getBody()).toString();
        }
    }

    @GetMapping("/refund")
    public String refund() throws InterruptedException {
        currentVisitor();
        Thread.sleep(1 + (long)(Math.random()*500));
        Optional.ofNullable(tracer.activeSpan()).ifPresent(as -> as.setBaggageItem("transaction", "refund"));
        try (Scope scope = tracer.buildSpan("RefundEvent").startActive(true)) {
            scope.span().setTag("refund", "visitor");
            ResponseEntity<String> response = restTemplate.getForEntity(eventUrl, String.class);
            return new StringBuffer().append("Visitor ").append(visitor.getName()).append(" needs REFUND + ").append(response.getBody()).toString();
        }
    }

    private void currentVisitor() {
        visitor = restTemplate.getForEntity(visitorUrl, VisitorDTO.class).getBody();
    }
}
