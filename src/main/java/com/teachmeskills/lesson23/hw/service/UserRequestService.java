package com.teachmeskills.lesson23.hw.service;

import java.util.Scanner;

import com.teachmeskills.lesson23.hw.consts.IParser;

public class UserRequestService {

    public static String getParserType() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(IParser.CHOOSE_PARSER_TYPE_MESSAGE);
        String type = scanner.nextLine();
        scanner.close();
        return type;
    }
}
