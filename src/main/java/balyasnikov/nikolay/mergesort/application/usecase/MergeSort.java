package balyasnikov.nikolay.mergesort.application.usecase;

import balyasnikov.nikolay.mergesort.application.model.SortableModel;
import balyasnikov.nikolay.mergesort.application.util.SortStrategy;

public interface MergeSort {
    <T> void execute(SortableModel sortableModel, SortStrategy<T> getElementForSort);
}
