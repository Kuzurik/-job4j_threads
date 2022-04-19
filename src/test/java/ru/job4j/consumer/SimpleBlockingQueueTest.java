package ru.job4j.consumer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(2);
        Thread offer = new Thread(() -> {
            try {
                queue.offer(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                queue.offer(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                queue.offer(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        Thread poll = new Thread(() -> {
            try {
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        offer.start();
        poll.start();
        offer.join();
        poll.join();
        assertThat(queue.poll(), is(2));
    }
}