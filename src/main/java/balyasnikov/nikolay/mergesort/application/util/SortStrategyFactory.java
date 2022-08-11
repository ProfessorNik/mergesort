package balyasnikov.nikolay.mergesort.application.util;

import balyasnikov.nikolay.mergesort.domain.value.SortMode;

public interface SortStrategyFactory {
    SortStrategy<?> create(SortMode sortMode);
}
