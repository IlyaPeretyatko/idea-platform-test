package ru.test.util;

import com.beust.jcommander.JCommander;
import lombok.Getter;

@Getter
public class CommandParser {

    private CommandArgs commandArgs;

    private JCommander jCommander;

    public void parse(String[] args) {
        commandArgs = new CommandArgs();
        jCommander = JCommander.newBuilder()
                .addObject(commandArgs)
                .build();
        jCommander.parse(args);
    }

}
