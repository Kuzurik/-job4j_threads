package ru.job4j.userstrage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {

    @GuardedBy("this")
    private final int id;

    @GuardedBy("this")
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public synchronized int getId() {
        return this.id;
    }

    public synchronized int getAmount() {
        return this.amount;
    }

    public synchronized void minusAmount(int amount) {
        this.amount -= amount;
    }

    public synchronized void plusAmount(int amount) {
        this.amount += amount;
    }
}
