package com.example.site.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class VisitorDTO {

    private String name;
    private String username;
    private String address;
    private String city;
    private String country;

}
