package sample;

import java.util.ArrayList;

public class tableListSingle {

    private String tables;
    private ArrayList<Table> tableListSingle;

    public tableListSingle() {
    }

    public tableListSingle(String tables, ArrayList<Table> tableListSingle) {
        this.tables = tables;
        this.tableListSingle = tableListSingle;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public ArrayList<Table> getTableListSingle() {
        return tableListSingle;
    }

    public void setTableListSingle(ArrayList<Table> tableListSingle) {
        this.tableListSingle = tableListSingle;
    }
}
