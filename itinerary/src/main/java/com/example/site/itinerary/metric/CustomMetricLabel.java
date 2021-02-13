package com.example.site.itinerary.metric;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomMetricLabel {

    FIND("find","n/a"),
    APPLICATION("appplication", "n/a" ),
    VERSION("version", System.getenv("VERSION"));

    private String key;
    private String value;


}
