package tables;

import java.util.*;

import model.DataTable;
import model.Row;

public class SearchTable implements DataTable {
	
	//declared variables
    private Row[] table;
    private final String name;
    private final List<String> columns;
    private final int degree;
    private int size;
    private int capacity;
    private static final int INITIAL_CAPACITY = 16;
    private int fingerprint;

    public SearchTable(String name, List<String> columns) {
        this.name = name;
        this.columns = List.copyOf(columns); 
        this.degree = columns.size();
        clear();}

    @Override
    public void clear() {
    	//clear method, resets all variables to default
        this.capacity = INITIAL_CAPACITY;
        this.table = new Row[capacity];
        this.size = 0;
        this.fingerprint = 0;}
//return methods
    @Override
    public int degree() {
        return this.degree;}
    @Override
    public int size() {
        return this.size;}
    @Override
    public int capacity() {
        return this.capacity;}
    @Override
    public String name() {
        return this.name;}
    @Override
    public List<String> columns() {
        return this.columns;}
    @Override
    public boolean isEmpty() {
        return this.size == 0;}
     @Override
    public boolean isFull() {
        return this.size == this.capacity;}
    @Override
    public double loadFactor() {
        return (double) this.size / this.capacity;}
    @Override
    public int hashCode() {
        return this.fingerprint; }
    //end of return methods
    
    @Override
    public boolean equals(Object obj) {
    	//checks if objects are the same and if object is an instance of the datatable
        if (this == obj) return true;
        if (!(obj instanceof DataTable)) return false; 
        DataTable other = (DataTable) obj;
        //are fingerprints the same?
        return this.fingerprint == other.hashCode();}

    
    //takes a key parameter (string) and a fields parameter (list of objects)
    @Override
    public List<Object> put(String key, List<Object> fields) {
        if (fields.size() != this.degree - 1) {
            throw new IllegalArgumentException("Row degree does not match table degree.");
        }
        //makes a new row made up of the key parameter + unmoddifiable view of list of the fields paramters
        Row newRow = new Row(key, Collections.unmodifiableList(fields));
        int rowHashCode = newRow.hashCode();
        
        //searches the array to find old row with specified/given key
        for (int i = 0; i < this.size; i++) {
            if (this.table[i].key().equals(key)) {
                Row oldRow = this.table[i];
             //on hit minus the hash code of the old row from the fingerprint
                this.fingerprint -= oldRow.hashCode();
             //replaces the old row at its index within the filled in area with new row
                this.table[i] = newRow;
             //adds updated hashcode to the fingerprint
                this.fingerprint += rowHashCode;
                return oldRow.fields();
            }
        }
        if (this.size == this.capacity) {
            expandCapacity();
        }
        this.table[this.size] = newRow;
        this.size++;
        this.fingerprint += rowHashCode;
        return null;
    }

    private void expandCapacity() {
    	//doubles capacity field with power of 2
        this.capacity *= 2;
        //reassigns array field with copyof
        this.table = Arrays.copyOf(this.table, this.capacity);
    }

    //get method
    //takes key paramter
    @Override
    public List<Object> get(String key) {
    	   //searches the array to find an old row with a key of specified parameters
        for (int i = 0; i < this.size; i++) {
            if (this.table[i].key().equals(key)) {
                return this.table[i].fields();
            }
        }
        return null;
    }

    @Override
    public List<Object> remove(String key) {
    	//searches array to find an old row with a key of specified parameter 
        for (int i = 0; i < this.size; i++) {
            if (this.table[i].key().equals(key)) {
                Row oldRow = this.table[i];
                this.fingerprint -= oldRow.hashCode();
                this.table[i] = this.table[this.size - 1];
                this.table[this.size - 1] = null;
                this.size--;
                return oldRow.fields();
            }
        }
        return null;
    }

    @Override
    public boolean contains(String key) {
        return get(key) != null;
    }
//
    @Override
    public Iterator<Row> iterator() {
        return new Iterator<Row>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < size;
            }

            @Override
            public Row next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[currentIndex++];
            }};}

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOf(this.table, this.size));}

    @Override
    public String toTabularView(boolean sorted) {
    	//takes boolean and returns it to a string
        StringBuilder builder = new StringBuilder();
        builder.append(name).append("\n");

        for (String column : columns) {
            builder.append(column).append("\t");
        }
        builder.append("\n");

        List<Row> rowList = new ArrayList<>(Arrays.asList(Arrays.copyOf(table, size)));
        if (sorted) {
            Collections.sort(rowList);
        }
        for (Row row : rowList) {
            builder.append(row.key()).append("\t");
            for (Object field : row.fields()) {
                builder.append(field).append("\t");
            }
            builder.append("\n");
        }
        return builder.toString();}}

