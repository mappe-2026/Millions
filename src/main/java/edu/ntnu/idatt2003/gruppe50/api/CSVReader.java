package edu.ntnu.idatt2003.gruppe50.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;

public class CSVReader {

    /**
     * Read lines from a file with a BufferedReader.
     * <p>
     *     Code gotten from code-example from lecture material.
     *     <a href="https://gitlab.com/atleolso/file-handling/-/blob/master/src/main/java/edu/ntnu/idatt2003/TextFiles.java">...</a>
     * </p>
     */
    public static void readLines(Path path) {
        try (BufferedReader bufferedReader = Files.newBufferedReader(path)) {
            String line;
        
            while ((line = bufferedReader.readLine()) != null) {
                if (line.isBlank() || line.startsWith("#")) continue;
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("No path found by: " + path);
        }
    }

    /**
     * Write text to a file with a BufferedWriter.
     * Try-with-resource closes the stream automatically.
     * <p>
     *     Code gotten from code-example from lecture material.
     *     <a href="https://gitlab.com/atleolso/file-handling/-/blob/master/src/main/java/edu/ntnu/idatt2003/TextFiles.java">...</a>
     * </p>
     */
    public static void writeToFile(Path path, String text) {
        try (Writer writer = Files.newBufferedWriter(path)) {
            writer.write(text);
        } catch (IOException e) {
            throw new IllegalArgumentException("No path found by: " + path);
        }
    }


}
