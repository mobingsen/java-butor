package org.plus.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PlayerLock {

    private final Lock l = new ReentrantLock();

    private int counter;

    public void lock() {
        l.lock();
    }

    public void unlock() {
        l.unlock();
    }

    public int inc() {
        return counter++;
    }

    public static void main(String[] args) {
        PlayerLock lock1 = new PlayerLock();
        PlayerLock lock2 = new PlayerLock();
        lock1.lock();
        lock2.lock();
        try {
            lock1.inc();
            lock2.inc();
        } catch (Exception e) {
            lock1.unlock();
            lock2.unlock();
        }
    }
}
