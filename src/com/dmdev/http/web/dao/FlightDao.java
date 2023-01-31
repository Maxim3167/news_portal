package com.dmdev.http.web.dao;

import com.dmdev.http.web.model.Flight;
import com.dmdev.http.web.model.FlightStatus;
import com.dmdev.http.web.util.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FlightDao implements Dao<Long, Flight> {
    //по правилу хорошего тона объекты сервисов, дао и сервлетов должны быть синглтонами
    // это класс дао слоя который обращается за информацией в БД и затем будет передавать ее в сервисный слой
    private static final FlightDao INSTANCE = new FlightDao();

    private FlightDao() {
    }

    private static final String FIND_ALL = """
            select * from flight
            """;
    @Override
    public List<Flight> findAll() {
        try (Connection connection = ConnectionManager.get()) {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Flight> flights = new ArrayList<>();
            while (resultSet.next()){
                flights.add(buildFlight(resultSet));
            }
            return flights;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Flight buildFlight(ResultSet resultSet) throws SQLException {
        return new Flight(resultSet.getObject("id",Long.class),
                resultSet.getObject("flight_no",String.class),
                resultSet.getObject("departure_date", LocalDateTime.class),
                resultSet.getObject("departure_airport_code",String.class),
                resultSet.getObject("arrival_date",LocalDateTime.class),
                resultSet.getObject("arrival_airport_code",String.class),
                resultSet.getObject("aircraft_id",Integer.class),
                FlightStatus.valueOf(resultSet.getObject("status",String.class)));
    }

    @Override
    public Optional<Flight> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public void update(Flight entity) {

    }

    @Override
    public Flight save(Flight entity) {
        return null;
    }

    public static FlightDao getInstance(){
        return INSTANCE;
    }


}
