package balyasnikov.nikolay.mergesort.application.model;

import java.io.Reader;
import java.io.Writer;
import java.util.List;


public class SortableModel {
    private List<Reader> sortable;
    private Writer sorted;

    public List<Reader> getSortable() {
        return sortable;
    }

    public void setSortable(List<Reader> sortable) {
        this.sortable = sortable;
    }

    public Writer getSorted() {
        return sorted;
    }

    public void setSorted(Writer sorted) {
        this.sorted = sorted;
    }

}
