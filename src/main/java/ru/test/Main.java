package ru.test;


import ru.test.util.CommandParser;
import ru.test.util.TicketParser;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            CommandParser commandParser = new CommandParser();
            commandParser.parse(args);
            TicketParser ticketParser = new TicketParser();
            String path = commandParser.getCommandArgs().getPath()
            ticketParser.parse(path);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
