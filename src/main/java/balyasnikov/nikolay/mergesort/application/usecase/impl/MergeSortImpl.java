package balyasnikov.nikolay.mergesort.application.usecase.impl;

import balyasnikov.nikolay.mergesort.application.model.SortableModel;
import balyasnikov.nikolay.mergesort.application.util.SortStrategy;
import balyasnikov.nikolay.mergesort.application.usecase.MergeSort;
import balyasnikov.nikolay.mergesort.domain.value.Sortable;


import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class MergeSortImpl implements MergeSort {
    public <T> void execute(SortableModel sortableModel, SortStrategy<T> strategy) {
        var sortableStreamScanners = getSortableStreamScanners(sortableModel);
        var sortedStreamPrinter = getSortedStreamPrinter(sortableModel);
		var scannedElements = new ArrayList<T>(sortableStreamScanners.size());

        var sortable = new Sortable<>(scannedElements, sortableStreamScanners, strategy);

        while (!sortable.isEmpty()){
            sortedStreamPrinter.println(sortable.getDownElement());
        }
    }

    private List<Scanner> getSortableStreamScanners(SortableModel sortableModel){
        var sortableReaders = sortableModel.getSortable();
        return mapReadersToScanner(sortableReaders);
    }

    private PrintWriter getSortedStreamPrinter(SortableModel sortableModel){
        var sortedWriter = sortableModel.getSorted();
        return new PrintWriter(sortedWriter);
    }

    private List<Scanner> mapReadersToScanner(List<Reader> readers){
        return readers.stream().map(Scanner::new).collect(Collectors.toList());
    }
}
