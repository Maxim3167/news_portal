package com.dmdev.http.web.service;

import com.dmdev.http.web.dto.FlightDto;
import com.dmdev.http.web.dao.FlightDao;

import java.util.List;
import java.util.stream.Collectors;

public class FlightService { //по правилу хорошего тона объекты сервисов, дао и сервлетов должны быть синглтонами
    // это класс сервисного слоя который обращается за информацией в дао слой
    private static final FlightService INSTANCE = new FlightService();
    private final FlightDao flightDao = FlightDao.getInstance();

    private FlightService() {
    }

    public List<FlightDto> findAll(){
      return flightDao.findAll().stream().map(flight -> new FlightDto(flight.getId(), """
              %s - %s - %s
              """.formatted(flight.getDepartureAirportCode(),flight.getArrivalAirportCode(),flight.getStatus())))
              .collect(Collectors.toList());
    }

    public static FlightService getInstance(){
        return INSTANCE;
    }
}
