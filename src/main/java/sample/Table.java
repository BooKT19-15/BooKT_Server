package sample;

public class Table {
    String seatCount;
    String tableCount;

    public Table() {
    }

    public Table(String seatCount, String tableCount) {
        this.seatCount = seatCount;
        this.tableCount = tableCount;
    }



    public String getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(String seatCount) {
        this.seatCount = seatCount;
    }

    public String getTableCount() {
        return tableCount;
    }

    public void setTableCount(String tableCount) {
        this.tableCount = tableCount;
    }


}
