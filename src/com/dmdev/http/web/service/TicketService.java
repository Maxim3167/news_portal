package com.dmdev.http.web.service;


import com.dmdev.http.web.dao.TicketDao;
import com.dmdev.http.web.dto.TicketDto;
import com.dmdev.http.web.model.Ticket;

import java.util.List;
import java.util.stream.Collectors;

public class TicketService {
    private final TicketDao ticketDao = TicketDao.getInstance();
    private static final TicketService INSTANCE = new TicketService();

    public List<TicketDto> findTicketsByFlightId(Long flightId){
        List<Ticket> tickets = ticketDao.findByFlightId(flightId);
       return tickets.stream().map(ticket -> new TicketDto(ticket.getId(),ticket.getFlightId(),ticket.getSeatNo()))
                .collect(Collectors.toList());
    }

    private TicketService() {
    }

    public static TicketService getInstance(){
        return INSTANCE;
    }
}
