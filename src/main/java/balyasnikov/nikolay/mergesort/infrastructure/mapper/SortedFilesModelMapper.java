package balyasnikov.nikolay.mergesort.infrastructure.mapper;

import balyasnikov.nikolay.mergesort.application.model.SortedFilesModel;
import balyasnikov.nikolay.mergesort.infrastructure.dto.SortOptionsDto;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SortedFilesModelMapper {
    public SortedFilesModel mapFromDto(SortOptionsDto sortOptionsDto) {
        var sortedFilesModel = new SortedFilesModel();
        sortedFilesModel.setInputFileNames(sortOptionsDto.getInputFileNames());
        sortedFilesModel.setInputFileReaders(openFiles(sortOptionsDto.getInputFileNames()));

        return sortedFilesModel;
    }

    private List<Reader> openFiles(List<String> fileNames) {
        var inputFileReaders = new ArrayList<Reader>();
        for(var fileName: fileNames){
            try {
                inputFileReaders.add(new FileReader(fileName));
            } catch (FileNotFoundException e) {
                throw new UncheckedIOException(e);
            }
        }
        return inputFileReaders;
    }

    public void mapToDto(SortedFilesModel sortedFilesModel, SortOptionsDto sortOptionsDto) {
        sortOptionsDto.setInputFileNames(sortedFilesModel.getInputFileNames());
        clearSortedFilesModel(sortedFilesModel);
    }

    public void clearSortedFilesModel(SortedFilesModel sortedFilesModel) {
        for(var inputFileReader: sortedFilesModel.getInputFileReaders()){
            try {
                inputFileReader.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        }
    }
}
