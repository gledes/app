package com.example.handlelib;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private LinkedList<Message> queue = new LinkedList<Message>();

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public Message next() {

        lock.lock();
        Message message = null;
        try {
            while (queue.size() == 0) {
                condition.await();
            }
            System.out.println("count:" + queue.size());
            message = queue.getFirst();
            queue.pop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return message;
    }

    public void add(Message message) {
        lock.lock();
        queue.addLast(message);
        condition.signalAll();
        lock.unlock();

    }
}
