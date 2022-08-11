package balyasnikov.nikolay.mergesort.domain.value;

import balyasnikov.nikolay.mergesort.domain.exception.InvalidFileFormatException;
import balyasnikov.nikolay.mergesort.application.util.SortStrategy;

import java.util.List;
import java.util.Scanner;

public class Sortable<T> {
    private final List<T> lastScannedSortableValues;
    private final List<Scanner> sortableStreams;
    private final SortStrategy<T> strategy;

    public Sortable(List<T> lastScannedSortableValues, List<Scanner> sortableStreams, SortStrategy<T> strategy) {
        this.lastScannedSortableValues = lastScannedSortableValues;
        this.sortableStreams = sortableStreams;
        this.strategy = strategy;

        scanFirstElements();
    }

    private void scanFirstElements(){
        for (Scanner sortableStream : sortableStreams) {
            if (!strategy.hasNextElement(sortableStream))
                throw new InvalidFileFormatException("stream is empty: " + sortableStream);
            var nextElement = strategy.scanElement(sortableStream);
            lastScannedSortableValues.add(nextElement);
        }
    }

    public T getDownElement(){
        var minElement = strategy.findDownElement(lastScannedSortableValues);
        var minElementIndex = lastScannedSortableValues.indexOf(minElement);
        scanNextElement(minElementIndex);
        return minElement;
    }

    public boolean isEmpty() {
        return sortableStreams.isEmpty();
    }

    private void scanNextElement(int index){
        var sortableStream = sortableStreams.get(index);

        if(strategy.hasNextElement(sortableStream)){
            var nextElement = strategy.scanElement(sortableStream);
            lastScannedSortableValues.set(index, nextElement);
        } else {
            removeStream(index);
        }
    }

    private void removeStream(int index){
        sortableStreams.remove(index);
        lastScannedSortableValues.remove(index);
    }
}
