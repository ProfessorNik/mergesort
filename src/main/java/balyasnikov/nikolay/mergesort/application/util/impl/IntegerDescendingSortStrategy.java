package balyasnikov.nikolay.mergesort.application.util.impl;

import java.util.List;

import static java.util.Collections.max;

public class IntegerDescendingSortStrategy extends IntegerSortStrategy {
    @Override
    public Integer findDownElement(List<Integer> dist) {
        return max(dist);
    }

    @Override
    public boolean correctOrder(Integer prev, Integer next) {
        return prev.compareTo(next) >= 0;
    }
}
