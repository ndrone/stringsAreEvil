package org.example;


import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

class StringsAreEvilTests {

    private static final long KILOBYTE = 1024L;

    private InputStream file;

    private static List<String> results = new ArrayList<>();

    @BeforeEach
    void init() {
        file = Thread.currentThread().getContextClassLoader().getResourceAsStream("strings.txt");
    }

    @AfterAll
    static void done() {
        results.forEach(System.out::println);
    }

    private static Stream<Arguments> getArguments() {
        return Stream.of(Arguments.of(new BaselineParser(), "Baseline"),
                Arguments.of(new EW1Parser(), "Easy Win 1"),
                Arguments.of(new EW2Parser(), "Easy Win 2"),
                Arguments.of(new NoSplitParser(), "No Line Split"),
                Arguments.of(new NoListParser(), "No List"),
                Arguments.of(new ParserWithByteArray(), "byte[]"),
                Arguments.of(new ParserWithByteArray2(), "byte[] 2"));
    }

    @ParameterizedTest
    @MethodSource("getArguments")
    void doWork(LineParser lineParser, String type) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(file));
            String readLine;
            Runtime runtime = Runtime.getRuntime();
            StopWatch stopWatch = StopWatch.createStarted();
            byte[] commaIndexes = new byte[7];
            while ((readLine = bufferedReader.readLine()) != null) {
                lineParser.parseLine(readLine, commaIndexes);
            }
            stopWatch.stop();
            results.add(type + ": " + stopWatch.getTime(TimeUnit.MILLISECONDS) + " milliseconds");
            runtime.gc();
            long memory = runtime.totalMemory() - runtime.freeMemory();
            results.add("Used memory is bytes: " + memory);
            results.add("Used memory is kilobytes: "
                    + bytesToKilobytes(memory));
            results.add("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static long bytesToKilobytes(long bytes) {
        return bytes / KILOBYTE;
    }
}
