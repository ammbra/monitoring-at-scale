package com.example.landmark.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LandmarkDTO  {

    private String name;
    private String address;
    private String city;
    private String country;
    private String message;
}
