package model;

public class DivisionIDCount {

    private int divID;
    private int divCount;

    public DivisionIDCount(int divID, int divCount) {
        this.divID = divID;
        this.divCount = divCount;
    }

    public int getDivID() {
        return this.divID;
    }

    public void setDivID(int divID) {
        this.divID = divID;
    }

    public int getDivCount() {
        return divCount;
    }

    public void setDivCount(int divCount) {
        this.divCount = divCount;
    }
}
