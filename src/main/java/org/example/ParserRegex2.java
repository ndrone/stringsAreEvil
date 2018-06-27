package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserRegex2 implements LineParser {

    private static final Pattern PATTERN_1 = Pattern.compile("(?:[^,]*,){1}([^,]*)");
    private static final Pattern PATTERN_2 = Pattern.compile("(?:[^,]*,){2}([^,]*)");
    private static final Pattern PATTERN_3 = Pattern.compile("(?:[^,]*,){3}([^,]*)");
    private static final Pattern PATTERN_4 = Pattern.compile("(?:[^,]*,){4}([^,]*)");
    private static final Pattern PATTERN_5 = Pattern.compile("(?:[^,]*,){5}([^,]*)");

    @Override
    public void parseLine(String line, byte[] commaIndexes) {
        parseLine(line);
    }

    @Override
    public void parseLine(String line) {

        if (line.startsWith("MNO")) {

            Integer elementId = parseSectionAsInteger(line, PATTERN_1);
            Integer vehicleId = parseSectionAsInteger(line, PATTERN_2);
            Integer term = parseSectionAsInteger(line, PATTERN_3);
            Integer mileage = parseSectionAsInteger(line, PATTERN_4);
            Double value = parseSectionAsDouble(line);
            ValueHolder valueHolder = new ValueHolder(elementId, vehicleId, term, mileage, value);
        }
    }

    private Integer parseSectionAsInteger(String line, Pattern pattern) {

        Matcher matcher = pattern.matcher(line);
        if (matcher.find()) {
            return Integer.valueOf(matcher.group(1));
        }
        return null;
    }

    private Double parseSectionAsDouble(String line) {

        Matcher matcher = PATTERN_5.matcher(line);
        if (matcher.find()) {
            return Double.valueOf(matcher.group(1));
        }
        return null;
    }
}
