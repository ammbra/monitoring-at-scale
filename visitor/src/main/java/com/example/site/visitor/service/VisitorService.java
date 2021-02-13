package com.example.site.visitor.service;


import com.example.site.visitor.dto.VisitorDTO;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class VisitorService {

    private Map<Integer, VisitorDTO> visitors;


    @PostConstruct
    private void init() {
        this.visitors = new HashMap<>();
        this.visitors.put(0, new VisitorDTO("Anonymous", "none", "Anonymous", "None", "None"));
        this.visitors.put(1, new VisitorDTO("Jane Doe", "jad", "Somewhere in London", "London", "United Kingdom"));
        this.visitors.put(2, new VisitorDTO("John Doe", "jod", "Somewhere in New York", "New York", "United States of America"));
        this.visitors.put(3, new VisitorDTO("Andrei Martin", "am", "Somewhere in Bucharest", "Bucharest", "Romania"));
    }

    public VisitorDTO getByUsername(String username) {
        return visitors.values().stream().filter(e -> e.getUsername().toLowerCase().contains(username)).findFirst().orElse(visitors.get(0));
    }

    public VisitorDTO getByIndex(Integer index) {
        return visitors.get(index);
    }
}
