package ru.test.util;

import com.beust.jcommander.Parameter;
import lombok.Getter;

@Getter
public class CommandArgs {

    @Parameter(
            names = {"-p", "--path"},
            required = true
    )
    private String path;

}
