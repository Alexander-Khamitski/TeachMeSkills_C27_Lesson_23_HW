package com.teachmeskills.lesson23.hw.model;

import java.util.ArrayList;
import java.util.Arrays;

public class Lines {

    private ArrayList<String> linesList;

    public ArrayList<String> getLinesList() {
        return linesList;
    }

    public void setLinesList(ArrayList<String> linesList) {
        this.linesList = linesList;
    }

    @Override
    public String toString() {
        return linesList.toString();
    }
}
