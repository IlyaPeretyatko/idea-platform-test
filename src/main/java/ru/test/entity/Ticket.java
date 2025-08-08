package ru.test.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ticket {

    @JsonProperty("origin")
    private String origin;

    @JsonProperty("origin_name")
    private String originName;

    @JsonProperty("destination")
    private String destination;

    @JsonProperty("destination_name")
    private String destinationName;

    @JsonProperty("departure_date")
    private String departureDate;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("arrival_date")
    private String arrivalDate;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("carrier")
    private String carrier;

    @JsonProperty("stops")
    private int stops;

    @JsonProperty("price")
    private int price;

    public String getDepartureDateTime() {
        return departureDate + " " + departureTime;
    }

    public String getArrivalDateTime() {
        return arrivalDate + " " + arrivalTime;
    }

    public int calculateFlightTime() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
        Date departure = format.parse(getDepartureDateTime());
        Date arrival = format.parse(getArrivalDateTime());
        long diffInMillis = arrival.getTime() - departure.getTime();
        return (int) TimeUnit.MILLISECONDS.toSeconds(diffInMillis);
    }
}
