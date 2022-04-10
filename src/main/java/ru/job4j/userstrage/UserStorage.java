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

    public synchronized void add(User user) {
        users.putIfAbsent(user.getId(), user);
    }

    public synchronized boolean delete(User user) {
       return users.remove(user.getId(), user);
    }

    public synchronized void update(User user) {
        users.replace(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User from = users.get(fromId);
        User dest = users.get(toId);
        if (from != null && dest != null && from.getAmount() >= amount) {
                dest.setAmount((from.getAmount() - amount) + dest.getAmount());
                from.setAmount(from.getAmount() - amount);
                update(from);
                update(dest);
                return true;
        }
        return false;
    }
}
