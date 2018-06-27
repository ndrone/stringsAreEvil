package org.example;

public interface LineParser {

    void parseLine(String line);

    void parseLine(String line, byte[] commaIndexes);
}
