package balyasnikov.nikolay.mergesort.application.util.impl;

import balyasnikov.nikolay.mergesort.application.util.SortStrategy;
import balyasnikov.nikolay.mergesort.application.util.SortStrategyFactory;
import balyasnikov.nikolay.mergesort.domain.value.SortMode;

public class SortStrategyFactoryImpl implements SortStrategyFactory {
    @Override
    public SortStrategy<?> create(SortMode sortMode) {
        return switch (sortMode) {
            case STRING_ASCENDING -> new StringAscendingSortStrategy();
            case STRING_DESCENDING -> new StringDescendingSortStrategy();
            case INTEGER_ASCENDING -> new IntegerAscendingSortStrategy();
            case INTEGER_DESCENDING -> new IntegerDescendingSortStrategy();
        };
    }
}
