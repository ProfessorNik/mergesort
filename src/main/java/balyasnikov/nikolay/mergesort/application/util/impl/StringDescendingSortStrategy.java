package balyasnikov.nikolay.mergesort.application.util.impl;

import java.util.List;

import static java.util.Collections.max;

public class StringDescendingSortStrategy extends StringSortStrategy{
    @Override
    public String findDownElement(List<String> dist) {
        return max(dist);
    }

    @Override
    public boolean correctOrder(String prev, String next) {
        return prev.compareTo(next) >= 0;
    }
}
