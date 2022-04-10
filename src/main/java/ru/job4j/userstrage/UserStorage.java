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
        if (user != null) {
            users.put(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(int id) {
        if (users.containsKey(id)) {
            users.remove(id);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user, int id) {
        if (users.containsKey(id)) {
            users.replace(id, user);
            return true;
        }
        return false;
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        users.get(fromId).minusAmount(amount);
        users.get(toId).plusAmount(amount);
    }
}
