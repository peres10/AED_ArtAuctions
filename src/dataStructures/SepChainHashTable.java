package dataStructures;  

/**
 * Separate Chaining Hash table implementation
 * @author AED  Team
 * @version 1.0
 * @param <K> Generic Key, must extend comparable
 * @param <V> Generic Value 
 */

public class SepChainHashTable<K extends Comparable<K>, V> 
    extends HashTable<K,V> 
{ 
	/**
	 * Serial Version UID of the Class.
	 */
    static final long serialVersionUID = 0L;

	/**
	 * The array of dictionaries.
	 */
    protected Dictionary<K,V>[] table;


    /**
     * Constructor of an empty separate chaining hash table,
     * with the specified initial capacity.
     * Each position of the array is initialized to a new ordered list
     * maxSize is initialized to the capacity.
     * @param capacity defines the table capacity.
     */
    @SuppressWarnings("unchecked")
    public SepChainHashTable( int capacity )
    {
        int arraySize = HashTable.nextPrime((int) (1.1 * capacity));
        // Compiler gives a warning.
        table = (Dictionary<K,V>[]) new Dictionary[arraySize];
        for ( int i = 0; i < arraySize; i++ )
            table[i] = new OrderedDoubleList<K,V>();
            //table[i] = null;
        maxSize = capacity;
        currentSize = 0;
    }                                      


    public SepChainHashTable( )
    {
        this(DEFAULT_CAPACITY);
    }                                                                

    /**
     * Returns the hash value of the specified key.
     * @param key to be encoded
     * @return hash value of the specified key
     */
    protected int hash( K key )
    {
        return Math.abs( key.hashCode() ) % table.length;
    }

    @Override
    public V find( K key )
    {
        return table[ this.hash(key) ].find(key);
    }

    @Override
    public V insert( K key, V value )
    {
        if ( this.isFull() )
            this.rehash();

        int hashVal = this.hash(key);
        V oldValue = table[hashVal].insert(key,value);

        if(oldValue == null)
            currentSize++;

        return oldValue;
    }

    @Override
    public V remove( K key )
    {
        int hashVal = this.hash(key);
        V oldValue = table[hashVal].remove(key);

        if(oldValue!=null)
            currentSize--;

        return oldValue;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( )
    {
        return new SepChainHashTableIterator();
    }

    private void rehash(){
        SepChainHashTable<K,V> newHashTable = new SepChainHashTable<>(table.length * 2);
        Iterator<Entry<K,V>> it = this.iterator();

        while (it.hasNext()){
            Entry<K,V> e = it.next();
            newHashTable.insert(e.getKey(), e.getValue());
        }

        this.maxSize = newHashTable.maxSize;
        this.table = newHashTable.table;
    }


    private class SepChainHashTableIterator implements Iterator<Entry<K,V>>{

        private int curIndex = 0;
        private Iterator<Entry<K,V>> curIterator = table[curIndex].iterator();

        @Override
        public boolean hasNext() {
            while (curIndex < table.length && !curIterator.hasNext()){
                curIndex++;
                if(curIndex < table.length){
                    curIterator = table[curIndex].iterator();
                }
            }
            return curIndex < table.length && curIterator.hasNext();
        }

        @Override
        public Entry<K, V> next() throws NoSuchElementException {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            return curIterator.next();
        }

        @Override
        public void rewind() {
            curIndex = 0;
            curIterator = table[curIndex].iterator();
        }
    }
}
































