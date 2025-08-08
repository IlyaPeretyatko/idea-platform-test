package ru.test.util;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class CommandArgs {

    @Parameter(
            names = {"-p", "--path"},
            description = "Путь до файла"
    )
    private String path = "tickets.txt";

    @Parameter(
            names = {"-o", "--origin"},
            description = "Точка отправления"
    )
    private String origin = "VVO";

    @Parameter(
            names = {"-d", "--destination"},
            description = "Точка назначения"
    )
    private String destination = "TLV";

    @Parameter(
            names = {"--help", "-h"},
            help = true
    )
    private boolean help = false;

}
