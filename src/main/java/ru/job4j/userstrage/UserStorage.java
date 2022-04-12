package ru.job4j.userstrage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }

    public synchronized boolean add(User user) {
        User result = users.putIfAbsent(user.getId(), user);
        return result != null;
    }

    public synchronized boolean delete(User user) {
       return users.remove(user.getId(), user);
    }

    public synchronized boolean update(User user) {
        User result = users.replace(user.getId(), user);
        return result != null;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User dest = users.get(toId);
        if (from != null && dest != null && from.getAmount() >= amount) {
                dest.setAmount(dest.getAmount() + amount);
                from.setAmount(from.getAmount() - amount);
                return true;
        }
        return false;
    }
}
