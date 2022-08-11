package balyasnikov.nikolay.mergesort.infrastructure.util;

import balyasnikov.nikolay.mergesort.infrastructure.dto.SortOptionsDto;
import org.apache.commons.cli.ParseException;

public interface CLIParser {
    SortOptionsDto parse(String[] args);
}
