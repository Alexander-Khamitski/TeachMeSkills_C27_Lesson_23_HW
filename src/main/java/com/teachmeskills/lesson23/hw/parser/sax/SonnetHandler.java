package com.teachmeskills.lesson23.hw.parser.sax;

import java.util.ArrayList;

import com.teachmeskills.lesson23.hw.consts.IAttribute;
import com.teachmeskills.lesson23.hw.model.Author;
import com.teachmeskills.lesson23.hw.model.Lines;
import com.teachmeskills.lesson23.hw.model.Sonnet;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.*;

import static com.teachmeskills.lesson23.hw.consts.IAttribute.AUTHOR;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.FIRST_NAME;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.LAST_NAME;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.LINE;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.LINES;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.NATIONALITY;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.SONNET;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.TITLE;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.YEAR_OF_BIRTH;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.YEAR_OF_DEATH;

public class SonnetHandler extends DefaultHandler {
    private Sonnet sonnet;
    private Author author;
    private Lines lines;
    private ArrayList<String> lineList = new ArrayList<>();
    private StringBuilder elementValue;

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) {
        switch (qName) {
            case SONNET:
                sonnet = new Sonnet();
                sonnet.setType(attr.getValue(IAttribute.TYPE));
            case AUTHOR:
                author = new Author();
                sonnet.setAuthor(author);
                break;
            case LINES:
                lines = new Lines();
                lines.setLinesList(lineList);
                sonnet.setLines(lines);
                break;
            case LAST_NAME:
            case FIRST_NAME:
            case NATIONALITY:
            case YEAR_OF_BIRTH:
            case YEAR_OF_DEATH:
            case TITLE:
            case LINE:
                elementValue = new StringBuilder();
                break;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        switch (qName) {
            case LAST_NAME:
                author.setLastName(elementValue.toString());
                break;
            case FIRST_NAME:
                author.setFirstName(elementValue.toString());
                break;
            case NATIONALITY:
                author.setNationality(elementValue.toString());
                break;
            case YEAR_OF_BIRTH:
                author.setYearOfBirth(Integer.parseInt(elementValue.toString()));
                break;
            case YEAR_OF_DEATH:
                author.setYearOfDeath(Integer.parseInt(elementValue.toString()));
                break;
            case TITLE:
                sonnet.setTitle(elementValue.toString());
                break;
            case LINE:
                lineList.add(elementValue.toString());
                break;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (elementValue == null) {
            elementValue = new StringBuilder();
        } else {
            elementValue.append(ch, start, length);
        }
    }

    public Sonnet getSonnet() {
        return sonnet;
    }
}