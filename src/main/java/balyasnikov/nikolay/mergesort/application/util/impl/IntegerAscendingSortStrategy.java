package balyasnikov.nikolay.mergesort.application.util.impl;

import java.util.List;

import static java.util.Collections.min;

public class IntegerAscendingSortStrategy extends IntegerSortStrategy{
    @Override
    public Integer findDownElement(List<Integer> dist) {
        return min(dist);
    }

    @Override
    public boolean correctOrder(Integer prev, Integer next) {
        return prev.compareTo(next) <= 0;
    }
}
