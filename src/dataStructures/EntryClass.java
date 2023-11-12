package dataStructures;

import java.io.Serializable;

public class EntryClass<K, V> implements Entry<K, V>, Serializable {

    private static final long serialVersionUID = 0L;

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

    protected void setKey(K newKey) {
        this.key = newKey;
    }

    protected void setValue(V newValue){
        this.value = newValue;
    }

}
