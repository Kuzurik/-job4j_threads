package ru.job4j.test;


import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


public class SumTest {

    @Test
    public void testSum() {
        int result = Sum.sum(2, 2);
        assertThat(result, is(4));
    }
}