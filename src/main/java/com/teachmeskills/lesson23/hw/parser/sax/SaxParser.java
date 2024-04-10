package com.teachmeskills.lesson23.hw.parser.sax;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.teachmeskills.lesson23.hw.model.Sonnet;
import org.xml.sax.SAXException;

import static com.teachmeskills.lesson23.hw.consts.IPath.PATH_TO_XML_FILE;

public class SaxParser {

    public static Sonnet parseSonnet() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            SonnetHandler sonnetHandler = new SonnetHandler();
            saxParser.parse(new File(PATH_TO_XML_FILE), sonnetHandler);
            return sonnetHandler.getSonnet();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

}
