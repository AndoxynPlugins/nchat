package net.daboross.bungeedev.nchat.data;

import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class FileUtils {

    public static void writeFile(File file, List<String> lines) throws IOException {
        if (lines == null || file == null) {
            throw new IllegalArgumentException();
        }
        file.getParentFile().mkdirs();
        if (!file.exists()) {
            file.createNewFile();
        }
        try (BufferedWriter bf = new BufferedWriter(new FileWriter(file))) {
            for (int i = 0; i < lines.size(); i++) {
                bf.write(lines.get(i));
                bf.newLine();
            }
        }
    }

    public static List<String> readFile(File file) throws FileNotFoundException, IOException {
        ArrayList<String> lines = new ArrayList<String>();
        try (FileReader fr = new FileReader(file); BufferedReader bf = new BufferedReader(fr)) {
            while (true) {
                String line = bf.readLine();
                if (line == null) {
                    break;
                }
                lines.add(line);
            }
        }
        return lines;
    }
}
