package org.example;

public class ParserWithByteArray2 implements LineParser {

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
            Integer elementId = parseSectionAsIntege(commaIndexes[0] + 1, commaIndexes[1], line);
            Integer vehicleId = parseSectionAsIntege(commaIndexes[1] + 1, commaIndexes[2], line);
            Integer term = parseSectionAsIntege(commaIndexes[2] + 1, commaIndexes[3], line);
            Integer mileage = parseSectionAsIntege(commaIndexes[3] + 1, commaIndexes[4], line);
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
        StringBuilder sb = new StringBuilder();

        for (int index = start; index < end; index++) {
            sb.append(line.charAt(index));
        }

        return Double.valueOf(sb.toString());
    }

    private Integer parseSectionAsIntege(int start, int end, String line) {
        StringBuilder sb = new StringBuilder();

        for (int index = start; index < end; index++) {
            sb.append(line.charAt(index));
        }

        return Integer.valueOf(sb.toString());
    }
}
