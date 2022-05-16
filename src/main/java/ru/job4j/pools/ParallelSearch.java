package ru.job4j.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelSearch extends RecursiveTask<Integer> {

    private final int[] array;
    private final int el;
    private final int from;
    private final int to;

    public ParallelSearch(int[] array, int el, int from, int to) {
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
        ParallelSearch left = new ParallelSearch(array, el, from, mid);
        ParallelSearch right = new ParallelSearch(array, el, mid + 1, to);
        left.fork();
        right.fork();
        return Math.max(left.join(), right.join());
    }

    private int findElement() {
        int result = 0;
        for (int i = from; i < to; i++) {
            if (el == (array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static Integer search(int[] array, int value) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(
                new ParallelSearch(array, value, 0, array.length)
        );
    }
}
