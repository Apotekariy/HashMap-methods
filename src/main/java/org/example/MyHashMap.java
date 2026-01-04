package org.example;

public class MyHashMap<K, V> {

    private static class Entry<K, V> {
        private final K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private final int SIZE = 5; // для создания коллизий
    private final Entry<K, V>[] table;
    private int size = 0;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new Entry[SIZE];
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode()) % SIZE;
    }

    public void put(K key, V value) {
        int hash = getBucketIndex(key);
        Entry<K, V> e = table[hash];

        if (e == null) {
            table[hash] = new Entry<>(key, value);
        } else {
            while (e.next != null) {
                if (e.getKey().equals(key)) {
                    e.setValue(value);
                    return;
                }
                e = e.next;
            }

            // Проверяем последний элемент
            if (e.getKey().equals(key)) {
                e.setValue(value);
                return;
            }

            e.next = new Entry<>(key, value);
        }
        size++;
    }

    public V get(K key) {
        int hash = getBucketIndex(key);
        Entry<K, V> e = table[hash];

        while (e != null) {
            if (e.getKey().equals(key)) {
                return e.getValue();
            }
            e = e.next;
        }
        return null;
    }

    public boolean containsKey(K key) {
        int hash = getBucketIndex(key);
        Entry<K, V> e = table[hash];

        while (e != null) {
            if (e.getKey().equals(key)) {
                return true;
            }
            e = e.next;
        }
        return false;
    }

    public Entry<K, V> remove(K key) {
        int hash = getBucketIndex(key);
        Entry<K, V> e = table[hash];

        if (e == null) {
            return null;
        }

        if (e.getKey().equals(key)) {
            table[hash] = e.next;
            e.next = null;
            size--;
            return e;
        }

        Entry<K, V> prev = e;
        e = e.next;

        while (e != null) {
            if (e.getKey().equals(key)) {
                prev.next = e.next;
                e.next = null;
                size--;
                return e;
            }
            prev = e;
            e = e.next;
        }

        return null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        // Проходим по ВСЕМ ячейкам массива (корзинам)
        for (int i = 0; i < SIZE; i++) {
            Entry<K, V> temp = table[i];
            // Проходим по цепочке внутри ячейки
            while (temp != null) {
                sb.append(temp.key).append("=").append(temp.value).append(", ");
                temp = temp.next;
            }
        }
        if (sb.length() > 1) {
            sb.setLength(sb.length() - 2);
        }
        sb.append("}");
        return sb.toString();
    }
}