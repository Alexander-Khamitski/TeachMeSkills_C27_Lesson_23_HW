package com.teachmeskills.lesson23.hw.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.teachmeskills.lesson23.hw.model.Sonnet;

import static com.teachmeskills.lesson23.hw.consts.IFileService.DASH;
import static com.teachmeskills.lesson23.hw.consts.IFileService.FILE_NAME;
import static com.teachmeskills.lesson23.hw.consts.IPath.PARSED_DIR;

public class FileService {

    public static String getFileName(Sonnet sonnet) {
        return String.format(FILE_NAME,
                             sonnet.getAuthor().getFirstName(),
                             sonnet.getAuthor().getLastName(),
                             sonnet.getTitle());
    }

    public static void createFileIfNotExist(String path, String fileName) {
        File dir = new File(PARSED_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(path + DASH + fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
        }
    }

    public static void appendFile(String pathToFile, String value) {
        try {
            value += "\n";
            Files.write(Path.of(pathToFile), value.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void writeListToFile(String pathToFile, ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            appendFile(pathToFile, list.get(i));
        }
    }

    public static void writeParsedSonnetLinesToFile(Sonnet sonnet) {
        String fileName = FileService.getFileName(sonnet);
        FileService.createFileIfNotExist(PARSED_DIR, fileName);
        String pathToParsedFile = PARSED_DIR + fileName;
        FileService.writeListToFile(pathToParsedFile, sonnet.getLines().getLinesList());
    }
}
