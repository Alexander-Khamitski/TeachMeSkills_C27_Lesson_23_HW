package com.teachmeskills.lesson23.hw.parser.dom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.teachmeskills.lesson23.hw.model.Author;
import com.teachmeskills.lesson23.hw.model.Lines;
import com.teachmeskills.lesson23.hw.model.Sonnet;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import static com.teachmeskills.lesson23.hw.consts.IAttribute.AUTHOR;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.FIRST_NAME;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.LAST_NAME;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.LINE;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.NATIONALITY;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.SONNET;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.TITLE;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.TYPE;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.YEAR_OF_BIRTH;
import static com.teachmeskills.lesson23.hw.consts.IAttribute.YEAR_OF_DEATH;
import static com.teachmeskills.lesson23.hw.consts.IPath.PATH_TO_XML_FILE;

public class DomParser {

    public static Sonnet parseSonnet() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(PATH_TO_XML_FILE));
            return getSonnet(document);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            throw new RuntimeException(e);
        }
    }

    private static Sonnet getSonnet(Document document) {
        Sonnet sonnet = new Sonnet();
        String sonnetType = getSonnetType(document);
        sonnet.setType(sonnetType);
        String sonnetTitle = getSonnetTitle(document);
        sonnet.setTitle(sonnetTitle);

        Author author = getAuthor(document);
        sonnet.setAuthor(author);

        Lines lines = getLines(document);
        sonnet.setLines(lines);
        return sonnet;
    }

    private static String getSonnetType(Document document) {
        String type = null;
        NodeList nList = document.getElementsByTagName(SONNET);
        Node node = nList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            type = element.getAttribute(TYPE);
        }
        return type;
    }

    private static String getSonnetTitle(Document document) {
        String type = null;
        NodeList nList = document.getElementsByTagName(SONNET);
        Node node = nList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            type = element.getElementsByTagName(TITLE).item(0).getTextContent();
        }
        return type;
    }

    private static Author getAuthor(Document document) {
        Author author = new Author();
        NodeList nList = document.getElementsByTagName(AUTHOR);
        Node node = nList.item(0);
        if (node.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) node;
            author.setLastName(element.getElementsByTagName(LAST_NAME).item(0).getTextContent());
            author.setFirstName(element.getElementsByTagName(FIRST_NAME).item(0).getTextContent());
            author.setNationality(element.getElementsByTagName(NATIONALITY).item(0).getTextContent());
            author.setYearOfBirth(Integer.parseInt(element.getElementsByTagName(YEAR_OF_BIRTH).item(0).getTextContent()));
            author.setYearOfDeath(Integer.parseInt(element.getElementsByTagName(YEAR_OF_DEATH).item(0).getTextContent()));
        }
        return author;
    }

    private static Lines getLines(Document document) {
        Lines lines = new Lines();
        NodeList nList = document.getElementsByTagName(LINE);
        ArrayList<String> lineList = new ArrayList<>();
        for (int i = 0; i < nList.getLength(); i++) {
            Node node = nList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                lineList.add(element.getTextContent());
            }
        }
        lines.setLinesList(lineList);
        return lines;
    }
}
