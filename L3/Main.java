package problems.L3;

import problems.L3.Core_Classes.DataType;
import problems.L3.Core_Classes.Row;
import problems.L3.Spread_Sheet.Sheet;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Spreadsheet spreadsheet = new Spreadsheet();

        spreadsheet.createSheet("Sheet1", 5, 3);
//        spreadsheet.createSheet("Sheet2", 3, 3);
//        spreadsheet.createSheet("Sheet3", 3, 3);

        Sheet sheet1 = spreadsheet.getSheet(0);
//        Sheet sheet2 = spreadsheet.getSheet(1);
//        Sheet sheet3 = spreadsheet.getSheet(2);

        sheet1.addColumn();
        sheet1.addRow();
        sheet1.addColumn();
        sheet1.addRow();
//        sheet1.addColumn();
//        sheet1.addRow();

        sheet1.updateColumnType(0,DataType.NUMBER);
        sheet1.updateColumnType(1,DataType.TEXT);
        sheet1.updateColumnType(2,DataType.DATE);
        sheet1.updateColumnType(3,DataType.NUMBER);

        sheet1.updateData(0, 0, 123);
        sheet1.updateData(0, 1, "Hello");
        sheet1.updateData(0, 2,new Date());
        sheet1.updateData(0,3,129);

        sheet1.updateData(1, 0, 300);
        sheet1.updateData(1, 1, "Zoho");
        sheet1.updateData(1, 2,"2024-08-13");

        sheet1.updateData(2, 0, 200);
        sheet1.updateData(2, 1, "Abrar");
        sheet1.updateData(2, 2,"2002-09-02");

        sheet1.updateData(3, 0, 20);
        sheet1.updateData(3, 1, "PAM");
        sheet1.updateData(3, 2,"2020-10-03");

        sheet1.updateData(4, 0, 1000);
        sheet1.updateData(4, 1, "PAM");
        sheet1.updateData(4, 2,"2010-12-23");

        sheet1.updateData(5,0,121);
        sheet1.updateData(5,1,"KOHLI");
        sheet1.updateData(5,2,"02-13-2002");

        sheet1.sort(0,false);
        List<Row> filteredRows = sheet1.filterRows(row -> "PAM".equals(row.getCell(1).getData()));

        for (Row row : filteredRows) {
            for (int col = 0; col < sheet1.getNumberOfColumns(); col++) {
                Object data = row.getCell(col).getData();
                System.out.print(data + "  ");
            }
            System.out.println();
        }


        for (int i = 0; i < spreadsheet.getSheetCount(); i++) {
            System.out.println();
            printSheetData(spreadsheet.getSheet(i));
        }
    }

    private static void printSheetData(Sheet sheet) {
        System.out.println("Sheet Name: " + sheet.getName());

        int numRows = sheet.getNumberOfRows();
        int numCols = sheet.getNumberOfColumns();

        System.out.print("      ");
        for (int col = 0; col < numCols; col++) {
            System.out.print(String.format("| %-10s ", col));
        }
        System.out.println("|");

        System.out.print("      ");
        for (int col = 0; col < numCols; col++) {
            System.out.print("+----------");
        }
        System.out.println("+");

        for (int row = 0; row < numRows; row++) {
            System.out.print(String.format("Row %-2d ", row));

            for (int col = 0; col < numCols; col++) {
                try {
                    Object data = sheet.getCellData(row, col);
                    System.out.print(String.format("| %-10s ", data != null ? data.toString() : "null"));
                } catch (Exception e) {
                    System.out.print("| Error     ");
                }
            }
            System.out.println("|");

            System.out.print("      ");
            for (int col = 0; col < numCols; col++) {
                System.out.print("+----------");
            }
            System.out.println("+");
        }
    }
}
