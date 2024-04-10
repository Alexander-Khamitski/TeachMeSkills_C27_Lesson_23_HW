package com.teachmeskills.lesson23.hw.service;

import com.teachmeskills.lesson23.hw.model.Sonnet;
import com.teachmeskills.lesson23.hw.parser.dom.DomParser;
import com.teachmeskills.lesson23.hw.parser.sax.SaxParser;

import static com.teachmeskills.lesson23.hw.consts.IParser.DOM;
import static com.teachmeskills.lesson23.hw.consts.IParser.SAX;
import static com.teachmeskills.lesson23.hw.consts.IParser.UNEXPECTED_PARSER_TYPE_MESSAGE;

public class ParseService {

    public static Sonnet parseSonnetByType(String type) {
        switch (type) {
            case SAX:
                return SaxParser.parseSonnet();
            case DOM:
                return DomParser.parseSonnet();
            default:
                System.out.println(UNEXPECTED_PARSER_TYPE_MESSAGE + type);
                return null;
        }
    }
}
