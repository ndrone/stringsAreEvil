package org.example;

public class BaselineParser implements LineParser {

    @Override
    public void parseLine(String line, byte[] commaIndexes) {
        parseLine(line);
    }

    @Override
    public void parseLine(String line) {

        String[] parts = line.split(",");
        if (parts[0].equals("MNO")) {
            ValueHolder valueHolder = new ValueHolder(line);
        }
    }
}
