package balyasnikov.nikolay.mergesort.infrastructure.dto;

import balyasnikov.nikolay.mergesort.domain.value.SortMode;

import java.util.List;

public class SortOptionsDto {
    private SortMode mode;
    private List<String> inputFileNames;
    private String outputFileName;

    public SortMode getMode() {
        return mode;
    }

    public void setMode(SortMode mode) {
        this.mode = mode;
    }

    public List<String> getInputFileNames() {
        return inputFileNames;
    }

    public void setInputFileNames(List<String> inputFileNames) {
        this.inputFileNames = inputFileNames;
    }

    public String getOutputFileName() {
        return outputFileName;
    }

    public void setOutputFileName(String outputFileName) {
        this.outputFileName = outputFileName;
    }


    @Override
    public String toString() {
        return "SortOptionsDto{" +
                "mode=" + mode +
                ", inputFileNames=" + inputFileNames +
                ", outputFileNames='" + outputFileName + '\'' +
                '}';
    }
}
