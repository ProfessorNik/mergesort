package balyasnikov.nikolay.mergesort.application.util.impl;

import java.util.List;

import static java.util.Collections.min;

public class StringAscendingSortStrategy extends StringSortStrategy{
    @Override
    public String findDownElement(List<String> dist) {
        return min(dist);
    }

    @Override
    public boolean correctOrder(String prev, String next) {
        return prev.compareTo(next) <= 0;
    }
}
