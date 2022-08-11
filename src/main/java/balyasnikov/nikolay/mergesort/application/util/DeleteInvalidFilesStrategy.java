package balyasnikov.nikolay.mergesort.application.util;

import java.util.Scanner;

public interface DeleteInvalidFilesStrategy<T> {
    boolean hasNextElement(Scanner scanner);

    T scanElement(Scanner scanner);

    boolean isInvalidElement(T element);
    boolean correctOrder(T prev, T next);
}
