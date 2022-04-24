package ru.job4j.cas;


import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void whenUseTwoThreads() throws InterruptedException {
        final CASCount casCount = new CASCount(0);
        Thread firstThread = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        casCount.increment();
                    }
                }
        );
        Thread secondThread = new Thread(
                () -> {
                    for (int i = 0; i != 3; i++) {
                        casCount.increment();
                    }
                }
        );
        firstThread.start();
        secondThread.start();
        firstThread.join();
        secondThread.join();
        assertThat(casCount.get(), is(6));
    }

}