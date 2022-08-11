package balyasnikov.nikolay.mergesort;

import balyasnikov.nikolay.mergesort.application.usecase.impl.DeleteInvalidFilesImpl;
import balyasnikov.nikolay.mergesort.application.usecase.impl.MergeSortImpl;
import balyasnikov.nikolay.mergesort.application.util.impl.DeleteInvalidFilesStrategyFactoryImpl;
import balyasnikov.nikolay.mergesort.application.util.impl.SortStrategyFactoryImpl;
import balyasnikov.nikolay.mergesort.domain.exception.InvalidFileFormatException;
import balyasnikov.nikolay.mergesort.infrastructure.controller.MergeSortController;
import balyasnikov.nikolay.mergesort.infrastructure.exception.CLIParseException;
import balyasnikov.nikolay.mergesort.infrastructure.util.impl.CLIParserWithApacheCommons;

import java.io.UncheckedIOException;

public class MergeSortRunner {
    public static void main(String[] args) {
        try {
            var parser = new CLIParserWithApacheCommons();
            var dto = parser.parse(args);
            var mergeSortController = new MergeSortController(new MergeSortImpl(),
                    new SortStrategyFactoryImpl(),
                    new DeleteInvalidFilesImpl(),
                    new DeleteInvalidFilesStrategyFactoryImpl());
            mergeSortController.deleteUnsortedFiles(dto);
            mergeSortController.sortFilesData(dto);
            System.out.println("Files " + dto.getInputFileNames() + " have been sorted");
            System.out.println("Result in file: " + dto.getOutputFileName());
        } catch (UncheckedIOException e){
            System.out.println("Input | Output error: " + e.getMessage());
        } catch (CLIParseException e){
            System.out.println("Command line parse error: " + e.getCause().getMessage());
        } catch (InvalidFileFormatException e) {
            System.out.println("Invalid file format: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
