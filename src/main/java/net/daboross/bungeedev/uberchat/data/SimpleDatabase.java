/*
 * Copyright (C) 2013 Dabo Ross <www.daboross.net>
 */
package net.daboross.bungeedev.uberchat.data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author daboross
 */
public class SimpleDatabase {

    private final Map<String, String> values = new HashMap<>();

    public SimpleDatabase() {
    }

    public SimpleDatabase(File file) throws FileNotFoundException, IOException {
        load0(file);
    }

    public String get(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
        return values.get(key);
    }

    public void set(String key, String value) {
        if (key == null) {
            throw new NullPointerException();
        }
        if (value == null) {
            values.remove(key.toLowerCase());
        } else {
            values.put(key.toLowerCase(), value);
        }
    }

    public void load(File file) throws FileNotFoundException, IOException {
        load0(file);
    }

    private void load0(File file) throws FileNotFoundException, IOException {
        List<String> lines = FileUtils.readFile(file);
        for (String line : lines) {
            String[] split = line.split("|", 2);
            if (split.length != 2) {
                System.out.println("Error reading database file " + file.getAbsolutePath() + ". Invalid line " + line);
            }
            values.put(split[0], split[1]);
        }
    }

    public void save(File file) throws IOException {
        List<String> lines = new ArrayList<>(values.size());
        for (Map.Entry<String, String> entry : values.entrySet()) {
            lines.add(entry.getKey() + "|" + entry.getValue());
        }
        System.out.println("Lines " + lines);
        FileUtils.writeFile(file, lines);
    }
}
