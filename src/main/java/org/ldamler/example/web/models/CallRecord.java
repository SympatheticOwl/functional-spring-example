package org.ldamler.example.web.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CallRecord {
    private String customerId;
    private String callId;
    private Long  startTimestamp;
    private Long endTimestamp;
}
