package com.example.handlelib;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MessageQueue {

    private Message[] queue = new Message[50];

    private int index = 0;

    private int count = 0;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public Message next() {

        lock.lock();
        Message message = null;
        try {
            while (count <= 0) {
                condition.await();
            }
            System.out.println("count:" + count);
            message = queue[index];
            index++;
            index = index == 50 ? 0 : index;
            count--;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return message;
    }

    public void add(Message message) {
        lock.lock();
        try {
            while (count == 50) {
                condition.await();
            }
            queue[index] = message;
            count++;
            condition.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}
