package ru.test;

import ru.test.entity.Ticket;
import ru.test.service.TicketService;
import ru.test.util.CommandParser;
import ru.test.util.TicketParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            CommandParser commandParser = new CommandParser();
            commandParser.parse(args);
            TicketParser ticketParser = new TicketParser();
            String path = commandParser.getCommandArgs().getPath();
            ticketParser.parse(path);
            List<Ticket> tickets = ticketParser.getTickets();
            Map<String, Integer> minFlightTimes = TicketService.calculateMinFlightTimeForEachCarrier(tickets, "VVO", "TLV");
            for (String carrier : minFlightTimes.keySet()) {
                System.out.println(carrier + " " + minFlightTimes.get(carrier));
            }
            System.out.println(TicketService.calculateDifferenceBetweenAveragePriceAndMedian(tickets));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
