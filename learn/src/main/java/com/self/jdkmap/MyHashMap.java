package com.self.jdkmap;


/**
 * @author shichen
 * @create 2018/3/12
 * @desc
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    private static final float DEFAULT_LOAD_FACTOR = 0.75F;

    final float loadFactor;

    private Entry<K, V>[] table;

    /**
     * 使用的数组的大小
     */
    private int useSize = 0;

    private int newCapacity;

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        }
        this.loadFactor = loadFactor;
        table = new Entry[initialCapacity];
        this.newCapacity = initialCapacity;
    }

    @Override
    public V put(K k, V v) {
        //大于负载因子，扩容
        if (this.useSize >= this.newCapacity * this.loadFactor) {
            this.newCapacity = this.newCapacity << 1;
            Entry<K, V>[] newTable = new Entry[newCapacity];
            for (int i = 0; i < this.table.length; i ++) {
                Entry<K, V> entry = this.table[i];
                int h = entry.getKey().hashCode();
                int index = this.index(hash(h), this.newCapacity);
                newTable[index] = entry;
            }
            this.table = newTable;
        }

        //放入值
        int index = this.index(hash(k.hashCode()), table.length);
        Entry<K, V> entry = this.table[index];
        if (null == entry) {
            this.table[index] = new Entry<>(k, v);
        } else {
            Entry newEntry = new Entry<>(k, v);
            if (entry.getKey().hashCode() == k.hashCode()) {
                //两个对象完全相同，则替换
                if (entry.getKey().equals(k)) {
                    this.table[index] = newEntry;
                } else {
                    entry.next = newEntry;
                }
            }
        }
        useSize ++;
        return this.table[index].getValue();
    }

    @Override
    public V get(K k) {
        int index = index(hash(k.hashCode()), this.newCapacity);
        Entry<K, V> entry = this.table[index];
        return entry.getValue();
    }

    private int hash(int hashCode) {
        hashCode = hashCode ^ ((hashCode >>> 20) ^ (hashCode >>> 12));
        return hashCode ^ ((hashCode >>> 7) ^ (hashCode >>>4));
    }

    /**
     * 获取索引位
     *
     * @param h
     * @param length
     * @return
     */
    private int index(int h, int length) {
        return h & (length - 1);
    }

    static class Entry<K,V> implements MyMap.Entry<K,V> {

        K k;
        V v;
        Entry<K, V> next;

        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}
