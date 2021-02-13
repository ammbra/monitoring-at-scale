package com.example.site.order.metric;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CustomMetricLabel {

    TRANSACTION("transaction","buy"),
    APPLICATION("appplication", "n/a" ),
    VERSION("version", "0.0.1");

    private String key;
    private String value;


}
