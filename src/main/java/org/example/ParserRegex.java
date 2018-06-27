package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserRegex implements LineParser {


    @Override
    public void parseLine(String line, byte[] commaIndexes) {
        parseLine(line);
    }

    @Override
    public void parseLine(String line) {

        if (line.startsWith("MNO")) {

            Integer elementId = parseSectionAsInteger(line, 1);
            Integer vehicleId = parseSectionAsInteger(line, 2);
            Integer term = parseSectionAsInteger(line, 3);
            Integer mileage = parseSectionAsInteger(line, 4);
            Double value = parseSectionAsDouble(line, 5);
            ValueHolder valueHolder = new ValueHolder(elementId, vehicleId, term, mileage, value);
        }
    }


    private Double parseSectionAsDouble(int start, int end, String line) {
        StringBuilder sb = new StringBuilder();

        for (int index = start; index < end; index++) {
            sb.append(line.charAt(index));
        }

        return Double.valueOf(sb.toString());
    }

    private Integer parseSectionAsInteger(String line, int commaCountStart) {

        Pattern pattern = Pattern.compile("(?:[^,]*,){" + commaCountStart + "}([^,]*)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    private Double parseSectionAsDouble(String line, int commaCountStart) {

        Pattern pattern = Pattern.compile("(?:[^,]*,){" + commaCountStart + "}([^,]*)");
        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Double.valueOf(matcher.group(1));
        }
        return null;
    }
}
