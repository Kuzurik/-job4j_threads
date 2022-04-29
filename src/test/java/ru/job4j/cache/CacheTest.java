package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        assertThat(cache.getById(1), is(base));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        cache.delete(base);
        assertNull(cache.getById(1));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        assertTrue(cache.update(base));
        assertThat(cache.getById(1).getVersion(), is(2));
    }

    @Test (expected = OptimisticException.class)
    public void whenUpdateExpectingException() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base base1 = new Base(1, 2);
        cache.add(base);
        cache.update(base1);
    }
}