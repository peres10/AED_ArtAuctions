package dataStructures;

import java.io.Serializable;

public class EntryClass<K, V> implements Entry<K, V>, Serializable {

    static final long serialVersionUID = 0L;

    private K key;
    private V value;

    public EntryClass(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public K getKey() {
        return key;
    }

    @Override
    public V getValue() {
        return value;
    }

}
