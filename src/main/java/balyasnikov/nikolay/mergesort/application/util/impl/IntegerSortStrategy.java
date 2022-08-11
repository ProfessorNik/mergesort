package balyasnikov.nikolay.mergesort.application.util.impl;

import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategy;
import balyasnikov.nikolay.mergesort.application.util.SortStrategy;

import java.util.Scanner;

public abstract class IntegerSortStrategy implements SortStrategy<Integer>, DeleteInvalidFilesStrategy<Integer> {
    @Override
    public boolean hasNextElement(Scanner scanner){
        return scanner.hasNextInt();
    }

    @Override
    public Integer scanElement(Scanner scanner) {
        return scanner.nextInt();
    }

    @Override
    public boolean isInvalidElement(Integer element) {
        return false;
    }
}
