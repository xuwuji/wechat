package com.xuwuji.wechat.app.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {
    public static String getTextFromFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        return getText(reader);
    }

    public static String getTextFromResourceFile(String resourceName) throws IOException {
        InputStreamReader isReader = new InputStreamReader(FileUtil.class.getClassLoader().getResourceAsStream(resourceName));
        BufferedReader reader = new BufferedReader(isReader);
        return getText(reader);
    }

    private static String getText(BufferedReader reader) throws IOException {
        if (reader == null) {
            return null;
        }
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }

        reader.close();
        return stringBuilder.toString();
    }
}
