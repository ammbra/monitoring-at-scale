package com.example.site.visitor.controller;

import com.example.site.visitor.dto.VisitorDTO;
import com.example.site.visitor.service.VisitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class VisitorController {

    @Autowired
    private VisitorService visitorService;

    @GetMapping({"/visitor/{username}"})
    public VisitorDTO getVisitor(@PathVariable String username) throws InterruptedException {
        Thread.sleep(1 + (long)(Math.random()*500));
        if (Math.random() > 0.8) {
            throw new RuntimeException("Failed to find account");
        }
        return this.visitorService.getByUsername(username);
    }

    @GetMapping("/")
    public VisitorDTO getRandomVisitor() throws InterruptedException {
        Random random = new Random();
        return this.visitorService.getByIndex(random.ints(0, 4).findFirst().getAsInt());
    }

}
