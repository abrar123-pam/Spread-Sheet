package problems.L3.Spread_Sheet;

import problems.L3.Core_Classes.Cell;
import problems.L3.Core_Classes.Column;
import problems.L3.Core_Classes.DataType;
import problems.L3.Core_Classes.Row;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;

public class Sheet {
    private String name;
    private List<Row> rows;
    private List<Column> columns;

    public Sheet(String name, int initialNumberOfRows, int initialNumberOfColumns) {
        this.name = name;
        this.columns = new ArrayList<>(initialNumberOfColumns);
        for (int i = 0; i < initialNumberOfColumns; i++) {
            columns.add(new Column("Column" + i, DataType.TEXT));
        }
        this.rows = new ArrayList<>(initialNumberOfRows);
        for (int i = 0; i < initialNumberOfRows; i++) {
            rows.add(new Row(initialNumberOfColumns));
        }
    }

    public String getName() {
        return name;
    }

    public List<Row> getRows() {
        return rows;
    }

    public List<Column> getColumns() {
        return columns;
    }

    public int getNumberOfRows() {
        return rows.size();
    }

    public int getNumberOfColumns() {
        if (rows.isEmpty()) return 0;
        return columns.size();
    }

    public void addRow() {
        rows.add(new Row(getNumberOfColumns()));
    }

    public void deleteRow(int index) {
        if (index < 0 || index >= rows.size()) {
            throw new IndexOutOfBoundsException("Row index out of bounds.");
        }
        rows.remove(index);
    }

    public void addColumn() {
        columns.add(new Column("Column"+columns.size(),DataType.TEXT));
        for (Row row : rows) {
            row.addCell(new Cell(null));
        }
    }

    public void deleteColumn(int index) {
        columns.remove(index);
        for (Row row : rows){
            row.removeCell(index);
        }
    }

    public void updateData(int rowIndex, int colIndex, Object data) {
        validateIndices(rowIndex,colIndex);

        Column column = columns.get(colIndex);
        DataType columnType = column.getType();

        data = validateAndFormatData(data, columnType);

        Row row = rows.get(rowIndex);
        row.getCell(colIndex).setData(data);
    }
    private Object validateAndFormatData(Object data, DataType type) {
        if (!isValidDataForType(data, type)) {
            throw new IllegalArgumentException("Invalid data type for this column");
        }

        if (data instanceof Date && type == DataType.DATE) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format((Date) data);
        }

        return data;
    }

    private boolean isValidDataForType(Object data, DataType type) {
        switch (type) {
            case TEXT:
                return data instanceof String;
            case NUMBER:
                return data instanceof Number;
            case DATE:
                return data instanceof Date || data instanceof String;
            default:
                return false;
        }
    }

    public DataType getColumnType(int colIndex) {
        if (rows.isEmpty()) return DataType.TEXT;
        return columns.get(colIndex).getType();
    }

    public Object getCellData(int rowIndex, int colIndex) {
        return rows.get(rowIndex).getCell(colIndex).getData();
    }

    public void updateColumnType(int colIndex, DataType newType) {
        if (colIndex < 0 || colIndex >= columns.size()) {
            throw new IndexOutOfBoundsException("Column index out of bounds.");
        }
        columns.get(colIndex).setType(newType);
    }

    private void validateIndices(int rowIndex, int colIndex) {
        if (rowIndex < 0 || rowIndex >= rows.size()) {
            throw new IllegalArgumentException("Invalid row index: " + rowIndex);
        }
        if (colIndex < 0 || colIndex >= columns.size()) {
            throw new IllegalArgumentException("Invalid column index: " + colIndex);
        }
    }


    public void sort(int columnIndex, boolean ascending) {
        Collections.sort(rows, new Comparator<Row>() {
            @Override
            public int compare(Row row1, Row row2) {
                Cell cell1 = row1.getCell(columnIndex);
                Cell cell2 = row2.getCell(columnIndex);
                Comparable value1 = (Comparable) cell1.getData();
                Comparable value2 = (Comparable) cell2.getData();

                // Handle null values using Comparator.nullsFirst() or Comparator.nullsLast()
                Comparator<Comparable> comparator = Comparator.nullsFirst(Comparator.naturalOrder());
                int comparisonResult = comparator.compare(value1, value2);

                return ascending ? comparisonResult : -comparisonResult;
            }
        });
    }


    public List<Row> filterRows(Predicate<Row> condition) {
        List<Row> filteredRows = new ArrayList<>();
        for (Row row : rows) {
            if (condition.test(row)) {
                filteredRows.add(row);
            }
        }
        return filteredRows;
    }
}
