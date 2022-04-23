package ru.job4j.cas;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    public CASCount(Integer value) {
        count.set(value);
    }

    public void increment() {
        Integer ref;
        Integer temp;
        do {
            ref = count.get();
            temp = ref++;
        } while (!count.compareAndSet(ref, temp));
    }

    public int get() {
        Integer ref;
        Integer temp;
        do {
            ref = count.get();
            temp = ref;
        } while (!count.compareAndSet(ref, temp));
        return ref;
    }
}
