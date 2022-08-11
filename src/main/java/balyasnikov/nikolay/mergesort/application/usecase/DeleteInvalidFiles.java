package balyasnikov.nikolay.mergesort.application.usecase;

import balyasnikov.nikolay.mergesort.application.model.SortedFilesModel;
import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategy;

public interface DeleteInvalidFiles {
    <T>void execute(SortedFilesModel sortedFilesModel, DeleteInvalidFilesStrategy<T> strategy);
}
