package problems.L3.Core_Classes;

import java.util.ArrayList;
import java.util.List;

public class Row {
    private List<Cell> cells;
    public Row(int numberOfColumns) {
        cells = new ArrayList<>(numberOfColumns);
        for (int i = 0; i < numberOfColumns; i++) {
            cells.add(new Cell(null));
        }
    }

    public Cell getCell(int index){
        return cells.get(index);
    }

    public void addCell(Cell cell){
        cells.add(cell);
    }

    public void removeCell(int index) {
        cells.remove(index);
    }

    public int getNumberOfCells() {
        return cells.size();
    }


}
