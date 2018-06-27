package org.example;

import java.util.ArrayList;
import java.util.List;

public class NoSplitParser implements LineParser {

    @Override
    public void parseLine(String line, byte[] commaIndexes) {
        parseLine(line);
    }

    @Override
    public void parseLine(String line) {

        if (line.startsWith("MNO")) {

            List<Integer> commaIndexes = getCommaIndexes(line);
            Integer elementId = parseSectionAsIntege(commaIndexes.get(0) + 1, commaIndexes.get(1), line);
            Integer vehicleId = parseSectionAsIntege(commaIndexes.get(1) + 1, commaIndexes.get(2), line);
            Integer term = parseSectionAsIntege(commaIndexes.get(2) + 1, commaIndexes.get(3), line);
            Integer mileage = parseSectionAsIntege(commaIndexes.get(3) + 1, commaIndexes.get(4), line);
            Double value = parseSectionAsDouble(commaIndexes.get(4) + 1, commaIndexes.get(5), line);
            ValueHolder valueHolder = new ValueHolder(elementId, vehicleId, term, mileage, value);
        }
    }

    private List<Integer> getCommaIndexes(String line) {
        List<Integer> commaIndexes = new ArrayList<>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == ',') {
                commaIndexes.add(i);
            }
        }
        return commaIndexes;
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
