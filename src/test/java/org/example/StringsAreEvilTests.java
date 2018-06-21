package org.example;


import org.apache.commons.lang3.time.StopWatch;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

class StringsAreEvilTests {

    private static final long KILOBYTE = 1024L;

    private static InputStream file;

    private static List<String> results = new ArrayList<>();

    @BeforeAll
    static void setup() {
        file = Thread.currentThread().getContextClassLoader().getResourceAsStream("strings.txt");
    }

    @AfterAll
    static void done() {
        results.forEach(System.out::println);
    }

    @Test
    void baseline() {
        doWork(file, new BaselineParser(), "Baseline");
    }

    private void doWork(InputStream file, LineParser lineParser, String type) {
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(file));
            String readLine;
            Runtime runtime = Runtime.getRuntime();
            StopWatch stopWatch = StopWatch.createStarted();
            while ((readLine = bufferedReader.readLine()) != null) {
                lineParser.parseLine(readLine);
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
