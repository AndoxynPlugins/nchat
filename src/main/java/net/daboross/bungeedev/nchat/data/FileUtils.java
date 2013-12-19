/*
 * Copyright (C) 2013 Dabo Ross <http://www.daboross.net/>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.daboross.bungeedev.nchat.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
