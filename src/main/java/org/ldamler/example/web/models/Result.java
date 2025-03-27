package org.ldamler.example.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String customerId;
    private String date;
    private int maxConcurrentCalls;
    private Long timestamp;
    private Set<String> callIds;
}
