package balyasnikov.nikolay.mergesort.application.util.impl;

import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategy;
import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategyFactory;
import balyasnikov.nikolay.mergesort.domain.value.SortMode;

public class DeleteInvalidFilesStrategyFactoryImpl implements DeleteInvalidFilesStrategyFactory {

    @Override
    public DeleteInvalidFilesStrategy<?> create(SortMode sortMode) {
        return switch (sortMode) {
            case STRING_ASCENDING -> new StringAscendingSortStrategy();
            case STRING_DESCENDING -> new StringDescendingSortStrategy();
            case INTEGER_ASCENDING -> new IntegerAscendingSortStrategy();
            case INTEGER_DESCENDING -> new IntegerDescendingSortStrategy();
        };
    }
}
