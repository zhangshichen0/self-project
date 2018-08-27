package com.self.lock.reentrant_lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * 使用ReentrantLock与Condition配合完成一个简单的LinkedBlockQueue  单向链表
 *
 * @author shichen
 * @create 2018/6/11
 * @desc
 */
public class SimpleLinkedBlockQueue<E> {

    private Node<E> head = null;
    private Node<E> tail = null;

    private final int capacity;
    
    private final ReentrantLock takeLock = new ReentrantLock();
    private final Condition notEmpty = takeLock.newCondition();

    private final ReentrantLock putLock = new ReentrantLock();
    private final Condition notFull = putLock.newCondition();

    private AtomicInteger count = new AtomicInteger();


    public SimpleLinkedBlockQueue() {
        this(Integer.MAX_VALUE);
    }

    public SimpleLinkedBlockQueue(int capacity) {
        head = tail = new Node<>(null);
        this.capacity = capacity;
    }

    /**
     * 放元素
     *
     * @param value
     * @throws InterruptedException
     */
    public void put(E value) throws InterruptedException {
        Node<E> node = new Node<>(value);
        int c = -1;
        final ReentrantLock putLock = this.putLock;
        final AtomicInteger count = this.count;
        putLock.lockInterruptibly();
        try {
            while (count.get() == this.capacity) {
                notFull.await();
            }
            enqueue(node);
            c = count.getAndIncrement();
            if (c + 1 < this.capacity) {
                notFull.signal();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            putLock.unlock();
        }

        // 当c>0时说明队列中元素个数，从来没空，及没有线程处于同步队列，
        // 使用==0 说明Queue中至少有一个元素，即队列中不为空，所以唤醒同步队列中某个线程继续获取数据
        if (c == 0) {
            signalNotEmpty();
        }
    }

    /**
     * 获取元素
     *
     * @return
     * @throws InterruptedException
     */
    public E take() throws InterruptedException {
        E result = null;
        int c = 1;
        final ReentrantLock takeLock = this.takeLock;
        final AtomicInteger count = this.count;

        takeLock.lockInterruptibly();

        try {
            //==0 说明没有元素可取，则释放锁进入等待队列
            while (count.get() == 0) {
                notEmpty.await();
            }
            result = dequeue();
            c = count.getAndDecrement();
            if (c > 1) {
                notEmpty.signal();
            }
        } finally {
            takeLock.unlock();
        }
        //当当前元素的数量==容量时，take时，未-1，说明队列不满，所以唤醒某个线程，放元素
        if (c == capacity) {
            signalNotFull();
        }
        return result;
    }

    /**
     * 返回队列数量
     * @return
     */
    public int size() {
        return count.get();
    }

    /**
     * 移除元素
     * @param o
     * @return
     */
    public boolean remove(Object o) {
        if (null == o) {
            return false;
        }
        //移除元素是对整个队列的操作，放和取都暂停
        fullyLock();
        try {
            for (Node<E> trail = head, p = trail.next; p != null; trail = p, p = p.next) {
                if (o.equals(p.item)) {
                    unlink(p, trail);
                    return true;
                }
            }
        } finally {
            fullyUnLock();
        }

        return false;
    }

    /**
     * 移除连接
     *
     * @param p
     * @param trail
     */
    private void unlink(Node<E> p, Node<E> trail) {
        p.item = null;
        trail.next = p.next;
        if (tail == p) {
            tail = trail;
        }

        if (count.getAndDecrement() == capacity) {
            notFull.signal();
        }
    }

    private void fullyLock() {
        takeLock.lock();
        putLock.lock();
    }

    private void fullyUnLock() {
        takeLock.unlock();
        putLock.unlock();
    }

    private void signalNotEmpty() {
        //必须先获取condition相关的锁之后，才能进行signal操作
        final ReentrantLock notEmptyLock = this.takeLock;
        notEmptyLock.lock();
        try {
            notEmpty.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            notEmptyLock.unlock();
        }

    }

    private void signalNotFull() {
        //必须先获取condition相关的锁之后，才能进行signal操作
        final ReentrantLock putLock = this.putLock;
        putLock.lock();
        try {
            this.notFull.signal();
        } finally {
            putLock.unlock();
        }
    }

    private void enqueue(Node<E> node) {
        this.tail = this.tail.next = node;
    }

    /**
     *
     * @return
     */
    private E dequeue() {
        Node<E> h = head;
        Node<E> first = h.next;
        // help GC
        h.next = h;
        head = first;
        E x = first.item;
        first.item = null;
        return x;
    }

    /**
     * 定义节点类
     *
     * @param <E>
     */
    private class Node<E> {
        private E item;
        private Node<E> next;

        public Node(E item) {
            this.item = item;
        }
    }
}
