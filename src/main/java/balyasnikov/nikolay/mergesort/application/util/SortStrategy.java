package balyasnikov.nikolay.mergesort.application.util;

import java.util.List;
import java.util.Scanner;

public interface SortStrategy<T> {
    boolean hasNextElement(Scanner scanner);

    T scanElement(Scanner scanner);

    T findDownElement(List<T> dist);
}