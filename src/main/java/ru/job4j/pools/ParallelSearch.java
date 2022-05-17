package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch<T> extends RecursiveTask<Integer> {

    private final T[] array;
    private final T el;
    private final int from;
    private final int to;

    public ParallelSearch(T[] array, T el, int from, int to) {
        this.array = array;
        this.el = el;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            return findElement();
        }
        int mid = (from + to) / 2;
        ParallelSearch<T> left = new ParallelSearch<>(array, el, from, mid);
        ParallelSearch<T> right = new ParallelSearch<>(array, el, mid + 1, to);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    private Integer findElement() {
        int result = -1;
        for (int i = from; i < to; i++) {
            if (el.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static <T> Integer search(T[] array, T value) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(
                new ParallelSearch<>(array, value, 0, array.length)
        );
    }
}
