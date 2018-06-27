package org.example;

public class ParserSubString implements LineParser {

    private byte[] commaIndexes;

    @Override
    public void parseLine(String line, byte[] commaIndexes) {
        this.commaIndexes = commaIndexes;
        parseLine(line);
    }

    @Override
    public void parseLine(String line) {

        if (line.startsWith("MNO")) {

            getCommaIndexes(line);
            Integer elementId = parseSectionAsInteger(commaIndexes[0] + 1, commaIndexes[1], line);
            Integer vehicleId = parseSectionAsInteger(commaIndexes[1] + 1, commaIndexes[2], line);
            Integer term = parseSectionAsInteger(commaIndexes[2] + 1, commaIndexes[3], line);
            Integer mileage = parseSectionAsInteger(commaIndexes[3] + 1, commaIndexes[4], line);
            Double value = parseSectionAsDouble(commaIndexes[4] + 1, commaIndexes[5], line);
            ValueHolder valueHolder = new ValueHolder(elementId, vehicleId, term, mileage, value);
        }
    }

    private void getCommaIndexes(String line) {
        int counter = 0;
        for (byte i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                commaIndexes[counter++] = i;
            }
        }
    }


    private Double parseSectionAsDouble(int start, int end, String line) {
        return Double.valueOf(line.substring(start, end));
    }

    private Integer parseSectionAsInteger(int start, int end, String line) {
        return Integer.valueOf(line.substring(start, end));
    }
}
