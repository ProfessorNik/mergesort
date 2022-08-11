package balyasnikov.nikolay.mergesort.infrastructure.controller;

import balyasnikov.nikolay.mergesort.application.model.SortableModel;
import balyasnikov.nikolay.mergesort.application.usecase.DeleteInvalidFiles;
import balyasnikov.nikolay.mergesort.application.usecase.MergeSort;
import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategyFactory;
import balyasnikov.nikolay.mergesort.application.util.SortStrategyFactory;
import balyasnikov.nikolay.mergesort.infrastructure.dto.SortOptionsDto;
import balyasnikov.nikolay.mergesort.infrastructure.mapper.SortableModelMapper;
import balyasnikov.nikolay.mergesort.infrastructure.mapper.SortedFilesModelMapper;

public class MergeSortController {
    private final MergeSort mergeSort;
    private final SortableModelMapper sortableModelMapper = new SortableModelMapper();
    private final SortedFilesModelMapper sortedFilesModelMapper = new SortedFilesModelMapper();
    private final DeleteInvalidFiles deleteInvalidFiles;
    private final DeleteInvalidFilesStrategyFactory deleteInvalidFilesStrategyFactory;
    private final SortStrategyFactory strategyFactory;

    public MergeSortController(MergeSort mergeSort, SortStrategyFactory strategyFactory, DeleteInvalidFiles deleteInvalidFiles, DeleteInvalidFilesStrategyFactory deleteInvalidFilesStrategyFactory) {
        this.mergeSort = mergeSort;
        this.deleteInvalidFiles = deleteInvalidFiles;
        this.strategyFactory = strategyFactory;
        this.deleteInvalidFilesStrategyFactory = deleteInvalidFilesStrategyFactory;
    }

    public void deleteUnsortedFiles(SortOptionsDto sortOptions) {
        var sortedFilesModel = sortedFilesModelMapper.mapFromDto(sortOptions);
        deleteInvalidFiles.execute(sortedFilesModel, deleteInvalidFilesStrategyFactory.create(sortOptions.getMode()));
        sortedFilesModelMapper.mapToDto(sortedFilesModel, sortOptions);
    }

    public void sortFilesData(SortOptionsDto sortOptions) {
        SortableModel sortableModel = sortableModelMapper.mapFromDto(sortOptions);
        mergeSort.execute(sortableModel, strategyFactory.create(sortOptions.getMode()));
        sortableModelMapper.clearSortableModel(sortableModel);
    }
}
