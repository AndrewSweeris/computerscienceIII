import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;

public class ChainingHash {
    private HashValue[] hashTable;
    private int size;
    private double loadfactor;
    public ChainingHash() {
        hashTable = new HashValue[16];
        size=0;
        keyIndex = 0;
        loadfactor = .75;
    }

    public ChainingHash(int startSize) {
        hashTable = new HashValue[startSize];
        size=0;
        keyIndex = 0;
        loadfactor = .75;
    }

    private int keyIndex;
    /**
     * This function allows rudimentary iteration through the ChainingHash.
     * The ordering is not important so long as all added elements are returned only once.
     * It should return null once it has gone through all elements
     *
     * @return Returns the next element of the hash table. Returns null if it is at its end.
     */
    public Object getNextKey() {
        if (keyIndex==size) {
            return null;
        }
        while (keyIndex!=size&&hashTable[keyIndex]==null) {
            keyIndex++;
        }
        return keyIndex == size ? null : hashTable[keyIndex].value;
    }

    /**
     * resize method
     */
    private void resize() {
        int hashCode;
        HashValue[] temp = new HashValue[hashTable.length*2+1];
        for (HashValue hv : hashTable) {
            if (hv!=null) { // Ignores null objs
                hashCode = Math.abs(hv.value.hashCode()%temp.length);
                while (temp[hashCode]!=null) { // Avoids overwrites
                    hashCode = (hashCode + 1)%temp.length;
                }
                temp[hashCode]= new HashValue(hv.value,hv.count);
            }
        }
        hashTable=temp;
    }
    /**
     * Adds the key to the hash table.
     * If there is a collision, it should be dealt with by chaining the keys together.
     * If the key is already in the hash table, it increments that key's counter.
     *
     * @param keyToAdd : the key which will be added to the hash table
     */
    public void insert(String keyToAdd) {
        //If keyToAdd is already in the hash table, then increment its count.
        if (keyToAdd!=null) {
            keyToAdd = keyToAdd.toLowerCase();
            int i = findIndex(keyToAdd);
            if (i==-1){
            if (((double) size) / hashTable.length >= loadfactor) {
                resize();
            }
            insert(keyToAdd, Math.abs(keyToAdd.hashCode()%hashTable.length));
            if (((double) size) / hashTable.length >= loadfactor) {
                resize();
            }
        }
            else {
                hashTable[i].incrementCount();
            }
        }
    }
    private void insert(String keyToAdd, int hashCode) {
        if (hashTable[hashCode]==null) {
            hashTable[hashCode]=new HashValue(keyToAdd);
            size++;
        }
        else {
            insert(keyToAdd, (hashCode+1)% hashTable.length);
        }
    }

    /**
     * Returns the number of times a key has been added to the hash table.
     *
     * @param keyToFind : The key being searched for
     * @return returns the number of times that key has been added.
     */
    public int findCount(Object keyToFind) {
        keyToFind = keyToFind.toString().toLowerCase();
        int i = findIndex(keyToFind);
        if (i!=-1) return hashTable[i].count;
        return 0;
    }
    private int findIndex(Object keyToFind) {
        if (keyToFind!=null) {
            for (int i = 0; i < hashTable.length; i++) {
                if (hashTable[i]!=null)
                    if (hashTable[i].value==keyToFind)return i;
            }
        }
        return -1;
    }

    /**
     * Returns the number of keys in the hash table.
     *
     * @return return keys
     */
    public int getSize() {
        return size;
    }
    private class HashValue {
        public Object value;
        public int count;
        public HashValue(Object obj) {
            value=obj;
            count = 1;
        }
        public HashValue(Object obj, int x) {
            value=obj;
            count=x;
        }
        public void incrementCount() {
            count++;
        }
    }
}
