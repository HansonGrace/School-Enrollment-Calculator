package tables;

import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

import model.Row;
import model.Table;

public class LookupTable implements Table {
	/*
	 * TODO: For Module 0, test and debug
	 * the errors in this implementation.
	 * Grace Hanson
	 * 17 May 2024
	 */

	private int size; //new
	private Row[] rows;
	private String name;
	private List<String> columns;
	private int degree;
	

	// TODO: This constructor has 1 initialization errors.
	//
	public LookupTable(String name, List<String> columns) {
		//issue, name has not been initialized in the constuctor. fix:
		this.name = name;
		this.columns = columns;
		this.degree = columns.size();
		clear();
	}


	@Override
	public void clear() {
		size = 0;
		rows = new Row[52];
		
	}

	// TODO: This helper method has 1 logic error.
	private int indexOf(String key) {
		if (key.length() < 1)
			throw new IllegalArgumentException("Key must be at least 1 character");

		char c = key.charAt(0);
		if (c >= 'a' && c <= 'z')
			return c - 'a';
		else if (c >= 'A' && c <= 'Z')
			return c - 'A' + 26;
		else
			throw new IllegalArgumentException("Key must start with a lowercase or uppercase letter");
	}


	@Override
	public List<Object> put(String key, List<Object> fields) {
		//fixed missing guard: key and field can not be set to null
		if(fields == null|| key == null)
			throw new IllegalArgumentException("The fields and key cannot be null");
		if (1 + fields.size() < degree)
			throw new IllegalArgumentException("Row is too narrow");
		if (1 + fields.size() > degree)
			//fix: checks if the rows are too wide
			throw new IllegalArgumentException("Row is too wide");
        //calls a helper method to find out index corresponding to the math from lecture
		//finds where to put
		int i = indexOf(key);

		Row make = new Row(key, fields);
        //fix: fixes logic error in previous code. returns null if rows[i] is null.
		if (rows[i] != null) {
			Row old = rows[i];
			rows[i] = make;
			return old.fields();
		}		else {
			rows[i]=make;
			size++;
			
		}
		//will return null if rows[i] is nulll
		return null;
	}

	@Override
	public List<Object> get(String key) {
		int i = indexOf(key);
		//fixes logic error with if statement
		//checks if array does not equal null
       if(rows[i] != null) {
    	   return rows[i].fields();
       }
		return null;
	}

	@Override
	public List<Object> remove(String key) {
		int i = indexOf(key);

		if (rows[i] != null) {
			//fixes result error by fetching the field before it goes to null
			List<Object> fields = rows[i].fields();
			rows[i] = null;
			return fields;
		}
		size--;

		return null;
	}


	@Override
	public int degree() {
		return degree;
	}

	@Override
	public int size() {
		int size = 0;
		for (Row row: rows) {
			if(row != null) {
			//for each row in the array of rows, increment size by 1
			size++;
			}
		}
		//return size when done
		return size;
	}

	@Override
	public int hashCode() {
		int fingerprint = 0;
		for (Row row: rows)
			if (row != null)
				//fix: use += instead of ^=XOR operation
				fingerprint += row.key().hashCode() ^ row.fields().hashCode();
		return fingerprint;
	}

	@Override
	public boolean equals(Object obj) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Iterator<Row> iterator() {
		throw new UnsupportedOperationException();
	}

	@Override
	public String name() {
		return name;
	}


	@Override
	public List<String> columns() {
		return List.copyOf(columns);
	}

	@Override
	public String toString() {
		var sj = new StringJoiner(", ", name() + "<" + columns().get(0) + "=" + columns().subList(1, degree) + ">{", "}");
		for (var row: rows)
			if (row != null)
				sj.add(row.key() + "=" + row.fields());
		return sj.toString();
	}
}