package ru.job4j.asynchrony;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class RolColSumTest {

    private final int[][] matrix = {{0, 1, 2},
                                    {3, 4, 5},
                                    {6, 7, 8}};

    @Test
    public void whenSumComputingSerial() {
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        List<Integer> expected = List.of(3, 9, 12, 12, 21, 15);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i != matrix.length; i++) {
            result.add(sums[i].getRowSum());
            result.add(sums[i].getColSum());
        }
        assertThat(result, is(expected));
    }

    @Test
    public void whenSumComputingAsync() throws ExecutionException, InterruptedException {
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        List<Integer> expected = List.of(3, 9, 12, 12, 21, 15);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i != matrix.length; i++) {
            result.add(sums[i].getRowSum());
            result.add(sums[i].getColSum());
        }
        assertThat(result, is(expected));
    }
}