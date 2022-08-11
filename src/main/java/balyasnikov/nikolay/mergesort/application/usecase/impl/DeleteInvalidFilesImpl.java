package balyasnikov.nikolay.mergesort.application.usecase.impl;

import balyasnikov.nikolay.mergesort.application.model.SortedFilesModel;
import balyasnikov.nikolay.mergesort.application.usecase.DeleteInvalidFiles;
import balyasnikov.nikolay.mergesort.application.util.DeleteInvalidFilesStrategy;

import java.io.Reader;
import java.util.Scanner;

public class DeleteInvalidFilesImpl implements DeleteInvalidFiles {
    public <T> boolean isUnsorted(Reader inputStreamReader, DeleteInvalidFilesStrategy<T> strategy) {
        Scanner scanner = new Scanner(inputStreamReader);

        if(!strategy.hasNextElement(scanner)){
            return true;
        }

        T prevElement = strategy.scanElement(scanner);
        if(strategy.isInvalidElement(prevElement)){
            return true;
        }

        while (strategy.hasNextElement(scanner)){
            T nextElement = strategy.scanElement(scanner);
            if(strategy.isInvalidElement(nextElement) || !strategy.correctOrder(prevElement, nextElement)) {
                return true;
            }
            prevElement = nextElement;
        }

        return false;
    }

    @Override
    public<T> void execute(SortedFilesModel sortedFilesModel, DeleteInvalidFilesStrategy<T> strategy) {
        for(int i = 0; i < sortedFilesModel.getInputFileNames().size(); i++){
            if(isUnsorted(sortedFilesModel.getInputFileReaders().get(i), strategy)){
                System.out.println("File with name "
                        + sortedFilesModel.getInputFileNames().get(i)
                        + " invalid, it doesn't sort");
                sortedFilesModel.deleteFile(i);
                i--;
            }
        }
    }
}
