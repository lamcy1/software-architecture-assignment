package repository;

import java.io.*;
import java.util.*;

/**
 * Utility class providing basic CSV file read and write functionality.
 *
 * This class is used by repository classes to persist data in CSV format.
 */
public class CsvUtil {

    /**
     * Reads all rows from a CSV file while skipping the header row.
     *
     * Empty values are preserved by using split(",", -1).
     *
     * @param path path to the CSV file
     * @return list of CSV rows as String arrays
     */
    public static List<String[]> read(String path) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            boolean header = true;

            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                rows.add(line.split(",", -1)); // preserves empty fields
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read CSV file: " + path, e);
        }
        return rows;
    }

    /**
     * Writes CSV data to a file, including the header.
     *
     * Existing file contents will be overwritten.
     *
     * @param path   path to the CSV file
     * @param header CSV header line
     * @param lines  CSV data lines
     */
    public static void write(String path, String header, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            bw.write(header);
            bw.newLine();

            for (String l : lines) {
                bw.write(l);
                bw.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to write CSV file: " + path, e);
        }
    }
}

