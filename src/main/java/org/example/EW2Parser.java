package org.example;

public class EW2Parser implements LineParser {

    @Override
    public void parseLine(String line) {

        if (line.startsWith("MNO")) {
            ValueHolder valueHolder = new ValueHolder(line);
        }
    }
}
