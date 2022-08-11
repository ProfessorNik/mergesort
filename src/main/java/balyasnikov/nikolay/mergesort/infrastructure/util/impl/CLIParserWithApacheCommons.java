package balyasnikov.nikolay.mergesort.infrastructure.util.impl;

import balyasnikov.nikolay.mergesort.domain.value.SortMode;
import balyasnikov.nikolay.mergesort.infrastructure.dto.SortOptionsDto;
import balyasnikov.nikolay.mergesort.infrastructure.exception.CLIParseException;
import balyasnikov.nikolay.mergesort.infrastructure.util.CLIParser;
import org.apache.commons.cli.*;

import java.util.ArrayList;
import java.util.List;

public class CLIParserWithApacheCommons implements CLIParser {
    private static final String sortingStringOption = "s";
    private static final String sortingIntegerOption = "i";
    private static final String sortingAscendingOption = "a";
    private static final String sortingDescendingOption = "d";


    public SortOptionsDto parse(String[] args) {
        try {
            var options = new Options()
                    .addOption(new Option(sortingStringOption, "Sorting string"))
                    .addOption(new Option(sortingIntegerOption, "Sorting integer"))
                    .addOption(new Option(sortingAscendingOption, "Sorting ascending"))
                    .addOption(new Option(sortingDescendingOption, "Sorting descending"));
            var parser = new DefaultParser();
            var cli = parser.parse(options, args);
            return mapCliToSortOptionsDto(cli);
        } catch (ParseException e){
            throw new CLIParseException(e);
        }
    }

    public SortOptionsDto mapCliToSortOptionsDto(CommandLine cli) throws ParseException {
        SortOptionsDto dto = new SortOptionsDto();

        checkForIncompatibleOptions(cli);
        mapSortMode(dto, cli);
        mapFileNames(dto, cli);

        return dto;
    }

    private void checkForIncompatibleOptions(CommandLine cli) throws ParseException {
        if(cli.hasOption(sortingStringOption) && cli.hasOption(sortingIntegerOption)){
            throw new ParseException("Options " + sortingStringOption +
                    " & " + sortingIntegerOption + "can't be used together ");
        }
        if(cli.hasOption(sortingAscendingOption) && cli.hasOption(sortingDescendingOption)){
            throw new ParseException("Options " + sortingDescendingOption +
                    " & " + sortingAscendingOption + "can't be used together ");
        }
    }
    private void mapSortMode(SortOptionsDto dto, CommandLine cli) throws ParseException {
        if(cli.hasOption(sortingStringOption)){
            if (cli.hasOption(sortingDescendingOption)) {
                dto.setMode(SortMode.STRING_DESCENDING);
            } else {
                dto.setMode(SortMode.STRING_ASCENDING);
            }
        } else if (cli.hasOption(sortingIntegerOption)) {
            if(cli.hasOption(sortingDescendingOption)){
                dto.setMode(SortMode.INTEGER_DESCENDING);
            } else {
                dto.setMode(SortMode.INTEGER_ASCENDING);
            }
        } else {
            throw new ParseException("unknown datatype, use: " + sortingStringOption + " or " + sortingIntegerOption);
        }
    }

    private void mapFileNames(SortOptionsDto dto, CommandLine cli) throws ParseException {
        String outputFileName = null;
        List<String> inputFileNames = new ArrayList<>();

        var args = cli.getArgList();

        boolean isOutputFileName = true;
        for(var arg : args){
            if(arg.startsWith("-")){
                continue;
            }
            if(isOutputFileName){
                outputFileName = arg;
                isOutputFileName = false;
            } else {
                inputFileNames.add(arg);
            }
        }

        if(outputFileName == null){
            throw new ParseException("output file name missed");
        }
        if(inputFileNames.isEmpty()){
            throw new ParseException("input file names missed");
        }

        dto.setInputFileNames(inputFileNames);
        dto.setOutputFileName(outputFileName);
    }
}
