package com.dmdev.http.web.dto;

import lombok.Value;

@Value
public class TicketDto {
    Long id;
    Long flightId;
    String seatNo;

}
