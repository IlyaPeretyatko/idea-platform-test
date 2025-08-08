package ru.test.service;

import ru.test.entity.Ticket;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketService {

    public static Map<String, Integer> calculateMinFlightTimeForEachCarrier(List<Ticket> tickets, String origin, String destination) {
        List<Ticket> filteredTickets = tickets.stream()
                .filter(ticket -> ticket.getOrigin().equals(origin) && ticket.getDestination().equals(destination))
                .toList();
        Map<String, Integer> map = new HashMap<>();
        for (Ticket ticket : filteredTickets) {
            try {
                String carrier = ticket.getCarrier();
                int flightTime = ticket.getFlightTime();
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

    public static double calculateDifferenceBetweenAveragePriceAndMedian(List<Ticket> tickets) {
        return Math.abs(calculateAveragePrice(tickets) - calculateMedianPrice(tickets));
    }

    public static double calculateAveragePrice(List<Ticket> tickets) {
        if (tickets == null) return 0;
        return tickets.stream().mapToInt(Ticket::getPrice).average().orElse(0);
    }

    public static double calculateMedianPrice(List<Ticket> tickets) {
        if (tickets == null || tickets.isEmpty()) return 0;
        int size = tickets.size();
        return size % 2 == 0 ? (tickets.get(size / 2).getPrice() + tickets.get(size / 2 - 1).getPrice()) / 2.0 : tickets.get(size / 2).getPrice();
    }
}
