package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        return memory.computeIfPresent(model.getId(),
                (k, v) -> {
                    int version = v.getVersion();
                    if (version != model.getVersion()) {
                            throw new OptimisticException("Versions are not equal");
                    }
                    v.setVersion(version + 1);
                    return v;
                }) != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

    public Base getById(int id) {
        return memory.get(id);
    }
}