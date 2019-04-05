package sample;

import java.util.ArrayList;

public class tableListFamily {
    private String tables;
    private ArrayList<Table> tableListFamily;

    public tableListFamily() {
    }

    public tableListFamily(String tables, ArrayList<Table> tableListFamily) {
        this.tables = tables;
        this.tableListFamily = tableListFamily;
    }

    public String getTables() {
        return tables;
    }

    public void setTables(String tables) {
        this.tables = tables;
    }

    public ArrayList<Table> getTableListFamily() {
        return tableListFamily;
    }

    public void setTableListFamily(ArrayList<Table> tableListFamily) {
        this.tableListFamily = tableListFamily;
    }
}
