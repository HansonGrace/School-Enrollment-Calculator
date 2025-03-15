package tables;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;
import model.FileTable;
import model.Row;

public class CSVTable implements FileTable {
//declare necesssary variables
    private static final Path BASE_DIRECTORY = Paths.get("db/sub/tables");
    private final Path path;

    public CSVTable(String name, List<String> columns) {
    try {
           //create base directory
            Files.createDirectories(BASE_DIRECTORY);

            //initializing variable
            this.path = BASE_DIRECTORY.resolve(name + ".csv");

            //if method: if file doesnt exist at path field, create it
            if (!Files.exists(this.path)) {
                Files.createFile(this.path);
                String header = String.join(",", columns);
                List<String> records = new ArrayList<>();
                records.add(header);
                Files.write(this.path, records);
            }
        } catch (IOException e) {
            throw new RuntimeException("failed to initialize the CSVTable", e); }}

    public CSVTable(String name) {
       //initializing variable
        this.path = BASE_DIRECTORY.resolve(name + ".csv");

        //if: file doesnt exist in path field, throw an exception error
        if (!Files.exists(this.path)) {
            throw new IllegalArgumentException("file doesnt exist " + this.path.toString());
        }
    }

    @Override
    public void clear() {
        try {
        	//read lines from file into record list
            List<String> records = Files.readAllLines(this.path);

            //clear everything and add the header
            if (!records.isEmpty()) {
                String header = records.get(0);
                records.clear();
                records.add(header);
            }
            Files.write(this.path, records);
        } catch (IOException e) {
            throw new RuntimeException("failed to clear the CSVTable", e);
        }
    }

    private static String encodeField(Object obj) {
        if (obj == null) {
            return "null";
        } else if (obj instanceof String) {
            return "\"" + obj + "\"";
        } else if (obj instanceof Boolean || obj instanceof Integer || obj instanceof Double) {
            return obj.toString();
        } else {
            throw new IllegalArgumentException("unsupported " + obj.getClass());
        }
    }

    private static Object decodeField(String field) {
        if (field.equalsIgnoreCase("null")) {
            return null;
        } else if (field.startsWith("\"") && field.endsWith("\"")) {
            return field.substring(1, field.length() - 1);
        } else if (field.equalsIgnoreCase("true") || field.equalsIgnoreCase("false")) {
            return Boolean.parseBoolean(field);
        } else {
            try {
                return Integer.parseInt(field);
            } catch (NumberFormatException e1) {
                try {
                    return Double.parseDouble(field);
                } catch (NumberFormatException e2) {
                    throw new IllegalArgumentException("unrecognized field " + field);
                }}}}
 private static String encodeRow(String key, List<Object> fields) {
        StringJoiner joiner = new StringJoiner(",");
        joiner.add(encodeField(key));
        for (Object field : fields) {
            joiner.add(encodeField(field));
        }
        return joiner.toString();
    }

    private static Row decodeRow(String record) {
        String[] fields = record.split(",");
        String key = fields[0].trim();
        List<Object> decodedFields = Arrays.stream(fields, 1, fields.length)
                                            .map(String::trim)
                                            .map(CSVTable::decodeField)
                                            .collect(Collectors.toList());
        return new Row(key, decodedFields);
    }
    @Override
    public List<Object> put(String key, List<Object> fields) {
        try {
            List<String> records = Files.readAllLines(this.path);
            String header = records.get(0);
            String newRecord = encodeRow(key, fields);
            String[] headerFields = header.split(",");
            if (fields.size() != headerFields.length - 1) {
                throw new IllegalArgumentException("the degree of the row does not match size of header");
            }
            List<Row> rows = records.stream().skip(1).map(CSVTable::decodeRow).collect(Collectors.toList());
            Optional<Row> oldRow = rows.stream().filter(row -> row.key().equals(key)).findFirst();
            if (oldRow.isPresent()) {
                rows.remove(oldRow.get());
            }
            rows.add(0, new Row(key, fields));
            List<String> newRecords = rows.stream().map(row -> encodeRow(row.key(), row.fields())).collect(Collectors.toList());
            newRecords.add(0, header);
            Files.write(this.path, newRecords);
            return oldRow.map(Row::fields).orElse(null);
        } catch (IOException e) {
            throw new RuntimeException("Failed to put row in CSVTable", e);
        }}

    @Override
    public List<Object> get(String key) {
        try {
            List<String> records = Files.readAllLines(this.path);
            List<Row> rows = records.stream().skip(1).map(CSVTable::decodeRow).collect(Collectors.toList());
            Optional<Row> row = rows.stream().filter(r -> r.key().equals(key)).findFirst();
            if (row.isPresent()) {
                rows.remove(row.get());
                rows.add(0, row.get());
                List<String> newRecords = rows.stream().map(r -> encodeRow(r.key(), r.fields())).collect(Collectors.toList());
                newRecords.add(0, records.get(0));
                Files.write(this.path, newRecords);
                return row.get().fields();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("Failed to get row from CSVTable", e);
        }}
    @Override
    public List<Object> remove(String key) {
        try {
            List<String> records = Files.readAllLines(this.path);
            List<Row> rows = records.stream().skip(1).map(CSVTable::decodeRow).collect(Collectors.toList());
            Optional<Row> row = rows.stream().filter(r -> r.key().equals(key)).findFirst();
            if (row.isPresent()) {
                rows.remove(row.get());
                List<String> newRecords = rows.stream().map(r -> encodeRow(r.key(), r.fields())).collect(Collectors.toList());
                newRecords.add(0, records.get(0));
                Files.write(this.path, newRecords);
                return row.get().fields();
            }
            return null;
        } catch (IOException e) {
            throw new RuntimeException("could not remove row", e);
        }}
    @Override
    public int degree() {
        try {
            List<String> records = Files.readAllLines(this.path);
            String header = records.get(0);
            return header.split(",").length;
        } catch (IOException e) {
            throw new RuntimeException("could not get degree", e);
        }}
    @Override
    public int size() {
        try {
            List<String> records = Files.readAllLines(this.path);
            return records.size() - 1;
        } catch (IOException e) {
            throw new RuntimeException("could not get size", e);
        }}
    @Override
    public int hashCode() {
        try {
            List<String> records = Files.readAllLines(this.path);
            return records.stream().skip(1).map(CSVTable::decodeRow).mapToInt(Row::hashCode).sum();
        } catch (IOException e) {
            throw new RuntimeException("not able to compute hash code", e);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CSVTable csvTable = (CSVTable) obj;
        return this.hashCode() == csvTable.hashCode();}

    @Override
    public Iterator<Row> iterator() {
        try {
            List<String> records = Files.readAllLines(this.path);
            List<Row> rows = records.stream().skip(1).map(CSVTable::decodeRow).collect(Collectors.toList());
            return rows.iterator();
        } catch (IOException e) {
            throw new RuntimeException("could not create the iterator", e);
        }}

    @Override
    public String name() {
        return path.getFileName().toString().replace(".csv", "");}

    @Override
    public List<String> columns() {
        try {
            List<String> records = Files.readAllLines(this.path);
            String header = records.get(0);
            return Arrays.asList(header.split(","));
        } catch (IOException e) {
            throw new RuntimeException("could not get columns", e);
        }}

    @Override
    public String toString() {
        try {
            List<String> records = Files.readAllLines(this.path);
            List<Row> rows = records.stream().skip(1).map(CSVTable::decodeRow).sorted().collect(Collectors.toList());
            return rows.toString();
        } catch (IOException e) {
            throw new RuntimeException("could not convert table to string", e);
        }}
    public static CSVTable fromText(String name, String text) {
        try {
            Files.createDirectories(BASE_DIRECTORY);

            //creates the path to the file
            Path newPath = BASE_DIRECTORY.resolve(name + ".csv");
            if (!Files.exists(newPath)) {
                Files.createFile(newPath);
            }
            Files.write(newPath, text.getBytes());
            //return new table
            return new CSVTable(name);
        } catch (IOException e) {
            throw new RuntimeException("Failed to create CSVTable from text", e);
        }}}
