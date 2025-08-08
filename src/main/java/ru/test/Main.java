package ru.test;

import com.beust.jcommander.JCommander;
import ru.test.entity.Ticket;
import ru.test.service.TicketAnalyzer;
import ru.test.util.CommandArgs;
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
            CommandArgs commandArgs = commandParser.getCommandArgs();
            String path = commandArgs.getPath();
            String origin = commandArgs.getOrigin();
            String destination = commandArgs.getDestination();

            TicketParser ticketParser = new TicketParser();
            ticketParser.parse(path);
            List<Ticket> tickets = ticketParser.getTickets();

            TicketAnalyzer ticketAnalyzer = new TicketAnalyzer(tickets);
            Map<String, Integer> minFlightTimes = ticketAnalyzer.calculateMinFlightTimeForEachCarrier(origin, destination);
            for (String carrier : minFlightTimes.keySet()) {
                System.out.println(carrier + " " + minFlightTimes.get(carrier));
            }
            System.out.println(ticketAnalyzer.calculateDifferenceBetweenAveragePriceAndMedian(origin, destination));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
