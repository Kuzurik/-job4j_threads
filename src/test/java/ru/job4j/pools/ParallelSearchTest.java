package ru.job4j.pools;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ParallelSearchTest {

    private final Integer[] array = fillArray();

    private Integer[] fillArray() {
        Integer[] array = new Integer[1000000];
        for (int i = 0; i != array.length; i++) {
            array[i] = i;
        }
        return array;
    }


    @Test
    public void whenExistElementReturnIndex() {
        assertThat(ParallelSearch.search(array, 999999), is(999999));
        assertThat(ParallelSearch.search(array, 500), is(500));
    }

    @Test
    public void whenNotExistElement() {
        assertThat(ParallelSearch.search(array, 1000001), is(-1));
        assertThat(ParallelSearch.search(array, -100), is(-1));
    }
}