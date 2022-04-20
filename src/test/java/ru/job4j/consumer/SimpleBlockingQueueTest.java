package ru.job4j.consumer;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(5);
        AtomicInteger result = new AtomicInteger();
        Thread offer = new Thread(() -> {
            for (int i = 0; i != 5; i++) {
                try {
                    queue.offer(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread poll = new Thread(() -> {
            for (int i = 0; i != 5; i++) {
                try {
                    result.set(queue.poll());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        offer.start();
        poll.start();
        offer.join();
        poll.join();
        assertThat(result.get(), is(4));
    }
}