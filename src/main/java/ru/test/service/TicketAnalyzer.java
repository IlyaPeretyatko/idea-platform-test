package ru.test.service;

import lombok.Getter;
import ru.test.entity.Ticket;

import java.text.ParseException;
import java.util.*;

@Getter
public class TicketAnalyzer {

    private final List<Ticket> tickets;

    public TicketAnalyzer(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Map<String, Integer> calculateMinFlightTimeForEachCarrier(String origin, String destination) {
        List<Ticket> filteredTickets = tickets.stream()
                .filter(ticket -> ticket.getOrigin().equals(origin) && ticket.getDestination().equals(destination))
                .toList();
        Map<String, Integer> map = new HashMap<>();
        for (Ticket ticket : filteredTickets) {
            try {
                String carrier = ticket.getCarrier();
                int flightTime = ticket.calculateFlightTime();
                if (map.containsKey(carrier)) {
                    if (flightTime < map.get(carrier)) {
                        map.put(carrier,flightTime);
                    }
                } else {
                    map.put(carrier, flightTime);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public double calculateDifferenceBetweenAveragePriceAndMedian(String origin, String destination) {
        List<Ticket> filteredTickets = tickets.stream()
                .filter(ticket -> ticket.getOrigin().equals(origin) && ticket.getDestination().equals(destination))
                .toList();
        return Math.abs(calculateAveragePrice(filteredTickets) - calculateMedianPrice(filteredTickets));
    }

    public static double calculateAveragePrice(List<Ticket> ticketsList) {
        if (ticketsList == null) return 0;
        return ticketsList.stream().mapToInt(Ticket::getPrice).average().orElse(0);
    }

    public static double calculateMedianPrice(List<Ticket> ticketsList) {
        if (ticketsList == null || ticketsList.isEmpty()) return 0;
        int size = ticketsList.size();
        return size % 2 == 0 ? (ticketsList.get(size / 2).getPrice() + ticketsList.get(size / 2 - 1).getPrice()) / 2.0 : ticketsList.get(size / 2).getPrice();
    }
}
