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

    @Test
    public void whenUpdateExpectingException() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setVersion(2);
        cache.update(base);
    }
}