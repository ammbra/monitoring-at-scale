package com.example.site.event.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class EventDTO {

    private String name;
    private String address;
    private String city;
    private String country;
    private String scheduleDate;

}
