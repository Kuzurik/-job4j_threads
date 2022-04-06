package ru.job4j.io;

import java.util.function.Predicate;

public interface Parse {

    String content(Predicate<Character> filter);
}
