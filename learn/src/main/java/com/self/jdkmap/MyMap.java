package com.self.jdkmap;

/**
 * @author shichen
 * @create 2018/3/12
 * @desc
 */
public interface MyMap<K, V> {
    /**
     * 放
     * @param k
     * @param v
     */
    V put(K k, V v);

    /**
     * 取
     * @param k
     */
    V get(K k);

    /**
     * 存储数据的entry
     *
     * @param <K>
     * @param <V>
     */
    interface Entry<K, V> {
        /**
         * 获得key
         *
         * @return
         */
        K getKey();

        /**
         * 获得value
         *
         * @return
         */
        V getValue();
    }
}
