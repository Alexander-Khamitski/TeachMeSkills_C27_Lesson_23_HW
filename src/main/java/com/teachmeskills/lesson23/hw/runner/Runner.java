package com.teachmeskills.lesson23.hw.runner;

import com.teachmeskills.lesson23.hw.consts.IParser;
import com.teachmeskills.lesson23.hw.model.Sonnet;
import com.teachmeskills.lesson23.hw.service.FileService;
import com.teachmeskills.lesson23.hw.service.ParseService;
import com.teachmeskills.lesson23.hw.service.UserRequestService;

public class Runner {

    public static void main(String[] args) {
        String type = UserRequestService.getParserType();
        Sonnet sonnet = ParseService.parseSonnetByType(type);
        System.out.println(IParser.PARSED_FILE_MESSAGE + sonnet);
        FileService.writeParsedSonnetLinesToFile(sonnet);
    }
}
