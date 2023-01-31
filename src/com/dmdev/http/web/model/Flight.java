package com.dmdev.http.web.model;


import lombok.Value;

import java.time.LocalDateTime;

@Value
public class Flight {
    Long id;
    String flight_no;
    LocalDateTime departureDate;
    String departureAirportCode;
    LocalDateTime arrivalDate;
    String arrivalAirportCode;
    Integer aircraftId;
    FlightStatus status;

    public Flight(Long id, String flight_no, LocalDateTime departureDate, String departureAirportCode, LocalDateTime arrivalDate, String arrivalAirportCode, Integer aircraftId, FlightStatus status) {
        this.id = id;
        this.flight_no = flight_no;
        this.departureDate = departureDate;
        this.departureAirportCode = departureAirportCode;
        this.arrivalDate = arrivalDate;
        this.arrivalAirportCode = arrivalAirportCode;
        this.aircraftId = aircraftId;
        this.status = status;
    }
}
