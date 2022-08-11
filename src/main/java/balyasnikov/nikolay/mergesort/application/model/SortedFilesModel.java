package balyasnikov.nikolay.mergesort.application.model;

import java.io.IOException;
import java.io.Reader;
import java.io.UncheckedIOException;
import java.util.List;

public class SortedFilesModel {
    private List<String> inputFileNames;
    private List<Reader> inputFileReaders;

    public void deleteFile(int index){
        inputFileNames.remove(index);
        try {
            inputFileReaders.get(index).close();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        inputFileReaders.remove(index);
    }

    public List<String> getInputFileNames() {
        return inputFileNames;
    }

    public void setInputFileNames(List<String> inputFileNames) {
        this.inputFileNames = inputFileNames;
    }

    public List<Reader> getInputFileReaders() {
        return inputFileReaders;
    }

    public void setInputFileReaders(List<Reader> inputFileReaders) {
        this.inputFileReaders = inputFileReaders;
    }
}
