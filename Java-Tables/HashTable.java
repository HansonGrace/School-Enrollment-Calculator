package tables;

import java.util.Iterator;
import java.util.List;
import model.DataTable;
import model.Row;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//TODO: Reformat code to look more readable
//public class
public class HashTable implements DataTable {
	//declare private variables
    private Row[] tableArray;
    private final String name;
    private final List<String> columns;
    private final int degree;
    private int size;
    private int capacity;
    private int fingerprint;
    //small prime number vs large number in M2
    private static final int INITIAL_CAPACITY = 11;
    private static final double LOAD_FACTOR_BOUND = 0.75;
    private static final Row TOMBSTONE = new Row(null, null);
    private int contamination;

    public HashTable(String name, List<String> columns) {
        this.name = name;
        this.columns = List.copyOf(columns);
        this.degree = columns.size();
        clear();
    }

    //clear method to reset all variables
    @Override
    public void clear() {
        capacity = INITIAL_CAPACITY;
        tableArray = new Row[capacity];
        size = 0;
        fingerprint = 0;
        contamination = 0;
    }
//FIX: updates hashfunction1 to have messagedigest
    private int hashFunction1(String key) {
        String salt = "Grace Hanson";
        String saltedKey = salt + key;
        return Math.floorMod(hash(saltedKey), capacity);
    }

    private int hashFunction2(String key) {
        String salt = "Grace Hanson";
        String saltedKey = salt + key;
        return 1 + Math.floorMod(hash(saltedKey), capacity - 1);
    }

    //hash method that allows hashfunction1 to look smoother
    private int hash(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(input.getBytes());
            int hash = 0;
            for (byte b : hashBytes) {
                hash = (hash * 31) + (b & 0xFF);
            }
            return hash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA256 is not allows", e);
        }
    }
//rehash method
    private void rehash() {
        Row[] oldArray = tableArray;
        int oldCapacity = capacity;
        capacity = nextPrime(capacity * 2);
        tableArray = new Row[capacity];
        size = 0;
        contamination = 0;
        fingerprint = 0;

        for (int i = 0; i < oldCapacity; i++) {
            if (oldArray[i] != null && oldArray[i] != TOMBSTONE) {
                put(oldArray[i].key(), oldArray[i].fields());
            }}}
//retrieves the next prime number. goes through list until it reaches one
    private int nextPrime(int n) {
        while (!isPrime(n)) {
            n++;
        }
        return n;}

    //checks if number is a prime number or not
    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;}

    @Override
    public List<Object> put(String key, List<Object> fields) {
        if (fields == null) {
            throw new IllegalArgumentException("Fields cant be null");
        }
        if (fields.size() != degree - 1) {
            throw new IllegalArgumentException("error degrees do not match");
        }
        List<Object> fieldsCopy = fields.stream()
                .map(obj -> obj != null ? obj : null).toList();
        Row newRow = new Row(key, fieldsCopy);
        int index = hashFunction1(key);
        int stepSize = hashFunction2(key);
        int tombstoneIndex = -1;

        while (tableArray[index] != null) {
            if (tableArray[index] == TOMBSTONE && tombstoneIndex == -1) {
                tombstoneIndex = index;
            }
            if (tableArray[index] != TOMBSTONE && tableArray[index].key().equals(key)) {
                Row oldRow = tableArray[index];
                fingerprint -= oldRow.hashCode();
                tableArray[index] = newRow;
                fingerprint += newRow.hashCode();
                return oldRow.fields();
            }
            index = (index + stepSize) % capacity;
        }
        if (tombstoneIndex != -1) {
            index = tombstoneIndex;
            contamination--;
        }
        tableArray[index] = newRow;
        size++;
        fingerprint += newRow.hashCode();

        if (loadFactor() > LOAD_FACTOR_BOUND) {
            rehash();
        }
        return null;
    }

    @Override
    public List<Object> get(String key) {
        int index = hashFunction1(key);
        int stepSize = hashFunction2(key);

        while (tableArray[index] != null) {
            if (tableArray[index] != TOMBSTONE && tableArray[index].key().equals(key)) {
                return tableArray[index].fields();
            }
            index = (index + stepSize) % capacity;
        }
        return null;
    }
    @Override
    public List<Object> remove(String key) {
        int index = hashFunction1(key);
        int stepSize = hashFunction2(key);
        while (tableArray[index] != null) {
            if (tableArray[index] != TOMBSTONE && tableArray[index].key().equals(key)) {
                Row oldRow = tableArray[index];
                tableArray[index] = TOMBSTONE;
                size--;
                contamination++;
                fingerprint -= oldRow.hashCode();
                return oldRow.fields();
            }
            index = (index + stepSize) % capacity;
        }
        return null;
    }

    //return methods
    @Override
    public int degree() {
        return degree;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public double loadFactor() {
        return (double) (size + contamination) / capacity;
    }

    @Override
    public int hashCode() {
        return fingerprint;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof DataTable)) return false;
        DataTable other = (DataTable) obj;
        return this.fingerprint == other.hashCode();
    }

    @Override
    public Iterator<Row> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                while (currentIndex < capacity && (tableArray[currentIndex] == null || tableArray[currentIndex] == TOMBSTONE)) {
                    currentIndex++;
                }
                return currentIndex < capacity;
            }

            @Override
            public Row next() {
                if (!hasNext()) {
                    throw new IllegalArgumentException("no next row is available");
                }
                return tableArray[currentIndex++];}};}

    @Override
    public String name() {
        return name;}
    
    @Override
    public List<String> columns() {
        return columns;}
    
    //builds into string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: ").append(name).append("\n");
        sb.append("Columns: ").append(columns).append("\n");
        sb.append("Rows:\n");
        for (Row row : tableArray) {
            if (row != null && row != TOMBSTONE) {
                sb.append(row.key()).append(": ").append(row.fields()).append("\n");
            }
        }
        return sb.toString(); }}
//end 
