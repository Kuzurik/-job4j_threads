package ru.job4j.cas;


import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void whenUseTwoThreads() {
        final List<Integer> list = new ArrayList<>();
        final CASCount casCount = new CASCount(0);
        Thread firstThread = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        casCount.increment();
                        list.add(casCount.get());

                    }
                }
        );
        Thread secondThread = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        casCount.increment();
                        list.add(casCount.get());
                    }
                }
        );
        firstThread.start();
        secondThread.start();
        assertThat(list, is(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }

}