package balyasnikov.nikolay.mergesort.infrastructure.mapper;

import balyasnikov.nikolay.mergesort.application.model.SortableModel;
import balyasnikov.nikolay.mergesort.infrastructure.dto.SortOptionsDto;

import java.io.*;
import java.util.ArrayList;

public class SortableModelMapper {
    public SortableModel mapFromDto(SortOptionsDto dto) {
        try {
            var inputFileNames = dto.getInputFileNames();
            var inputFilesReaders = new ArrayList<Reader>();
            for (var inputFileName : inputFileNames) {
                inputFilesReaders.add(new FileReader(inputFileName));
            }

            var outputFileName = dto.getOutputFileName();
            Writer outputFileWriter = new FileWriter(outputFileName);

            SortableModel sortableModel = new SortableModel();
            sortableModel.setSortable(inputFilesReaders);
            sortableModel.setSorted(outputFileWriter);
            return sortableModel;
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public void clearSortableModel(SortableModel model) {
        try {
            for (var outputFileReader : model.getSortable()) {
                outputFileReader.close();
            }

            var inputFileWriter = model.getSorted();
            inputFileWriter.close();
        } catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }
}
