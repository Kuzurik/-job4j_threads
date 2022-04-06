package ru.job4j.io;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.function.Predicate;

final public class ParseFile implements Parse {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    @Override
    public String content(Predicate<Character> filter) {
        StringBuilder out = new StringBuilder();
        try (InputStream in = new FileInputStream(file)) {
            int data;
            while ((data = in.read()) != -1) {
                if (filter.test((char) data)) {
                    out.append((char) data);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public String getContent() {
        return content(d -> true);
    }

    public String getContentWithoutUnicode() {
        return content(d -> d < 0x80);
    }
}
