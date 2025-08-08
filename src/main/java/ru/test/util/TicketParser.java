package ru.test.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import ru.test.entity.Ticket;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Getter
public class TicketParser {

    private final List<Ticket> tickets;

    public TicketParser() {
        tickets = new LinkedList<>();
    }

    public void parse(String path) throws IOException  {
        tickets.clear();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, List<Ticket>> ticketJson = objectMapper.readValue(
                new File(path),
                new TypeReference<>() {
                }
        );
        tickets.addAll(ticketJson.get("tickets"));
    }

}
