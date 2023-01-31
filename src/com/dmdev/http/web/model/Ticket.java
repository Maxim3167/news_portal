package com.dmdev.http.web.model;

import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
@Data
public class Ticket {
    Long id;
    String passengerNo;
    String passengerName;
    Long flightId;
    String seatNo;
    BigDecimal cost;

    public Ticket(Long id, String passengerNo, String passengerName, Long flightId, String seatNo, BigDecimal cost) {
        this.id = id;
        this.passengerNo = passengerNo;
        this.passengerName = passengerName;
        this.flightId = flightId;
        this.seatNo = seatNo;
        this.cost = cost;
    }
}


