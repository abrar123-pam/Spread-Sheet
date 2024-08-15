package problems.L3;

import problems.L3.Spread_Sheet.Sheet;

import java.util.ArrayList;
import java.util.List;

class Spreadsheet {
    private List<Sheet> sheets;
    public Spreadsheet()
    {
        this.sheets = new ArrayList<>();
    }

    public void createSheet(String name, int initialNumberOfRows, int initialNumberOfColumns) {
        sheets.add(new Sheet(name, initialNumberOfRows, initialNumberOfColumns));
    }

    public void deleteSheet(int index) {
        if (index < 0 || index >= sheets.size()) {
            throw new IndexOutOfBoundsException("Sheet index out of bounds.");
        }
        sheets.remove(index);
    }
    public Sheet getSheet(int index) {
        if (index < 0 || index >= sheets.size()) {
            throw new IndexOutOfBoundsException("Sheet index out of bounds.");
        }
        return sheets.get(index);
    }

    public int getSheetCount() {
        return sheets.size();
    }
}