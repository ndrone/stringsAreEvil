package org.example;

import java.io.File;
import java.io.IOException;

public class StringFileGenerator {

    private static File folder;

    public static void main(String[] args) {
        try {
            createTempFile();
            File file = newFile("strings.txt");

            FileContentGenerator fileContentGenerator = new FileContentGenerator();
            fileContentGenerator.generateContent(file);

            System.out.println("Move: " + file.getAbsolutePath() + " to src/main/resources for test cases");
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    private static void createTempFile() throws IOException {
        folder = File.createTempFile("junit", "");
        folder.delete();
        folder.mkdir();
    }

    private static File newFile(String fileName) throws IOException {
        File file = new File(getRoot(), fileName);
        if (!file.createNewFile()) {
            throw new IOException("a file with the name \'" + fileName + "\' already exists in the test folder");
        }
        return file;
    }

    private static File getRoot() {
        if (folder == null) {
            throw new IllegalStateException("the temporary folder has not yet been created");
        }
        return folder;
    }
}
