package hashsetsimple.structs;
import java.util.AbstractCollection;
import java.util.AbstractSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
public class MyMap<K,V> implements Map<K,V>
{
    private Entry<K,V>[] table;
    private int size;
    public MyMap() {
        this(4);
    }
    public MyMap(int xcap) {
        if(xcap <= 0){
            throw new IllegalArgumentException("Capacidad no permitida: " + xcap);
        }
        table = new Entry[xcap];
        size = 0;
    }
    public MyMap(Map<? extends K, ? extends V> m) {
        this(m.size());
        this.putAll(m);
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void clear() {
        for(int i = 0; i < table.length; i++){
            table[i] = null;
        }
        size = 0;
    }
    /*----------------------------------------------------------*/
    @Override
    public boolean containsValue(Object value) {
        for (Entry<K,V> entry : table) {
            for (Entry e = entry; e != null; e = e.next) {
                if (value != null && value.equals(e.getValue())){
                    return true;
                }
            }
        }
        return false;
    }
    /*----------------------------------------------------------*/
    @Override
    public V get(Object key) {
        return (this.getEntry(key) == null) ? null : this.getEntry(key).getValue();
    }
    @Override
    public boolean containsKey(Object key) {
        return this.getEntry(key) != null;
    }
    private Entry<K, V> getEntry(Object key) {
        int hash = hash(key,table.length);
        for (Entry e = table[hash]; e != null; e = e.next) {
            if(e.getKey().equals(key)){
                return e;
            }
        }
        return null;
    }
    /*----------------------------------------------------------*/
    @Override
    public V put(K key, V value) {
        if(key != null){
            int hash = hash(key,table.length);
            for(Entry<K,V> e = table[hash]; e != null; e = e.next){
                if(e.getKey().equals(key)){
                    V oldValue = e.getValue();
                    e.setValue(value);
                    return oldValue;
                }                
            }
            this.addEntry(key, value);
            return value;                     
        } else {
            return null;
        }
    }
    private void addEntry(K key, V value){
        if(size >= table.length * 0.75){
            Entry<K,V>[] tmp = table;
            size = 0;
            table = Arrays.copyOf(table, table.length * 2);
            for(int i = 0; i < table.length; i++){
                table[i] = null;
            }
            for (Entry<K, V> e : tmp) {     
                if(e != null){
                    put(e.getKey(),e.getValue());
                }
            }            
        }
        int hash = hash(key,table.length);
        table[hash] = new Entry(key, value,table[hash]);
        size++;
    }
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if(m.size() > 0){
            m.entrySet().forEach((e) -> {
                this.put(e.getKey(), e.getValue());
            });
        }
    }
    /*----------------------------------------------------------*/
    @Override
    public V remove(Object key) {
        int hash = hash(key, table.length);
        Entry last = null;
        for (Entry e = table[hash]; e != null; e = e.next) {
            if (key.equals(e.getKey())) {
                if (last == null) {
                    table[hash] = e.next;
                } else {
                    last.next = e.next;
                }
                size--;
                return (V) e.value;
            }
            last = e;
        }
        return null;
    }
    /*----------------------------------------------------------*/
    private int hash(Object key, int length) {
        return (key == null) ? 0 : (key.hashCode() & 0x7fffffff) % length;
    }
    /*----------------------------------------------------------*/

    

    

    @Override
    public Set<K> keySet() {
        return new KeySet();
    }   
    /*-----------------------------------------------------------*/
    private class KeySet extends AbstractSet<K>{
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
        @Override
        public int size() {
            return size;
        }        
    }
    private final class KeyIterator extends HashIterator<K> {
        @Override
        public K next() {
            return nextEntry().getKey();
        }        
    }
    /*-----------------------------------------------------------*/
    @Override
    public Collection<V> values() {
        return new Values();
    }
    private class Values extends AbstractCollection<V>{
        @Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
        @Override
        public int size() {
            return size;
        }        
    }
    private final class ValueIterator extends HashIterator<V>{
    @Override
        public V next() {
            return nextEntry().getValue();
        }        
    }
    /*-----------------------------------------------------------*/
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return new EntrySet();
    }
    private class EntrySet extends AbstractSet<Map.Entry<K,V>> {
        @Override
        public Iterator<Map.Entry<K, V>> iterator() {
            return new EntryIterator();
        }
        @Override
        public int size() {
            return size;
        }        
    }
    private final class EntryIterator extends HashIterator<Map.Entry<K,V>> {
        @Override
        public Entry<K, V> next() {
            return nextEntry();
        }        
    }
    /*-----------------------------------------------------------*/
    private abstract class HashIterator<E> implements Iterator<E> {
        private int index;
        private Entry<K,V> curr;
        private Entry<K,V> next;
        @SuppressWarnings("empty-statement")
        HashIterator() {
            curr = null;
            next = null;
            findEntry(0);
        }
        @Override
        public boolean hasNext() {
            return next != null;
        }
        @SuppressWarnings("empty-statement")
        public Entry<K,V> nextEntry() {
            /*
            currEntry = nextEntry;
            nextEntry = table[index];
            index++;            
            if (index <= size && table[index] != null) {
                nextEntry = table[index];               
            } else {
                nextEntry = null;
                for (;index < size; index++){
                    if (table[index] != null){
                        nextEntry = table[index];
                    }
                }
            }
            return currEntry;*/
            curr = next;
            next = next.next;
            if (next == null) {
                findEntry(index + 1);
            }
            return curr;
        }
        private void findEntry(int n) {
            for (int i = n; i < table.length; i++) {
                Entry entry = table[i];
                if (entry != null) {
                    next = entry;
                    index = i;
                    break;
                }
            }
        }
    }
    /*-----------------------------------------------------------*/
    class Entry<K,V> implements Map.Entry<K,V>{
        final K key;
        V value;
        Entry<K,V> next;
        public Entry(K xkey, V xvalue, Entry<K, V> xnext) {
            this.key = xkey;
            this.value = xvalue;
            this.next = xnext;
        }        
        @Override
        public K getKey() {
            return key;
        }
        @Override
        public V getValue() {
            return value;
        }
        @Override
        public V setValue(V v) {
            V val = value;
            value = v;
            return val;
        }        
    } 
}
