package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class FileContentGenerator {

    private final Map<String, Integer> map;
    private final List<Integer> terms = Arrays.asList(12, 24, 36, 48, 60);
    private final List<Integer> mileages = Arrays.asList(6000, 10000, 15000, 20000, 25000, 30000, 40000, 50000);
    private final List<Integer> values = Arrays.asList(1000, 2000, 5000, 10000, 25000);
    private final Random random = new Random(252028800);

    FileContentGenerator() {
        map = new HashMap<>(15);
        map.put("ABC", 1);
        map.put("BCD", 5);
        map.put("CDE", 25);
        map.put("DEF", 77);
        map.put("EFG", 4);
        map.put("FGH", 24);
        map.put("GHI", 4);
        map.put("HIJ", 144);
        map.put("IJK", 384);
        map.put("JKL", 276);
        map.put("KLM", 13);
        map.put("LMN", 2);
        map.put("MNO", 10036466);
        map.put("NOP", 279);
        map.put("OPQ", 9731);
    }

    void generateContent(File file) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            map.forEach((k, v) -> {
                for (int i = 0; i < v; i++) {
                    String line = String.join(",", k, String.valueOf(random.nextInt(10)),
                            String.valueOf(random.nextInt(999999)),
                            String.valueOf(terms.get(random.nextInt(terms.size()))),
                            String.valueOf(mileages.get(random.nextInt(mileages.size()))),
                            String.valueOf(values.get(random.nextInt(values.size()))));
                    try {
                        bw.write(line + ",,\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
