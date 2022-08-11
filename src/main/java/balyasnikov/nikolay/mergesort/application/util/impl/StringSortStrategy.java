package balyasnikov.nikolay.mergesort.application.util.impl;

import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategy;
import balyasnikov.nikolay.mergesort.application.util.SortStrategy;

import java.util.Scanner;

public abstract class StringSortStrategy implements SortStrategy<String>, DeleteInvalidFilesStrategy<String> {
    @Override
    public boolean hasNextElement(Scanner scanner) {
        return scanner.hasNextLine();
    }

    @Override
    public String scanElement(Scanner scanner) {
        return scanner.nextLine();
    }

    @Override
    public boolean isInvalidElement(String element) {
        return element.contains(" ");
    }

}
